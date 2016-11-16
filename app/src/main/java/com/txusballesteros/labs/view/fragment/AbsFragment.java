/*
 * Copyright Txus Ballesteros 2016 (@txusballesteros)
 *
 * This file is part of some open source application.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * Contact: Txus Ballesteros <txus.ballesteros@gmail.com>
 */
package com.txusballesteros.labs.view.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.txusballesteros.labs.AndroidApplication;
import com.txusballesteros.labs.R;
import com.txusballesteros.labs.di.ApplicationComponent;

abstract class AbsFragment extends Fragment {
  public static final int WITHOUT_MENU = 0;
  private Menu menu;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    onRequestInjection(getApplicationComponent());
    setHasOptionsMenu(fragmentHasOptionsMenu());
    initializeToolbar();
  }

  protected boolean fragmentHasOptionsMenu() {
    return true;
  }

  void onRequestInjection(ApplicationComponent applicationComponent) { }

  public void onInitializeToolbar() { }

  protected ActionBar getToolbar() {
    return ((AppCompatActivity) getActivity()).getSupportActionBar();
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater,
                           @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    int layoutResourceId = onRequestLayoutResourceId();
    View result = inflater.inflate(layoutResourceId, container, false);
    return result;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    doViewInjection(view);
    onPresenterShouldBeAttached();
    onViewReady();
    initializeToolbar();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    onPresenterShouldBeDetached();
  }

  protected Menu getMenu() {
    return menu;
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    this.menu = menu;
    this.menu.clear();
    int menuResourceId = onRequestMenuResourceId();
    if (menuResourceId != WITHOUT_MENU) {
      inflater.inflate(menuResourceId, this.menu);
    } else {
      super.onCreateOptionsMenu(menu, inflater);
    }
  }

  @MenuRes
  protected int onRequestMenuResourceId() {
    return WITHOUT_MENU;
  }

  @LayoutRes
  abstract int onRequestLayoutResourceId();

  private void initializeToolbar() {
    if (getToolbar() != null) {
      onInitializeToolbar();
    }
  }

  private void doViewInjection(View view) {
    View bindingView = view;
    if (isAdded()) {
      bindingView = getActivity().findViewById(R.id.rootView);
    }
    ButterKnife.bind(this, bindingView);
  }

  protected void onPresenterShouldBeAttached() { }

  protected void onPresenterShouldBeDetached() { }

  public void onViewReady() { }

  private ApplicationComponent getApplicationComponent() {
    return ((AndroidApplication) getActivity().getApplication()).getApplicationComponent();
  }
}
