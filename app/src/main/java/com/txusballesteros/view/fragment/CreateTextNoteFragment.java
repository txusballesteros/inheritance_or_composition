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
package com.txusballesteros.view.fragment;

import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import com.txusballesteros.R;
import com.txusballesteros.di.ApplicationComponent;
import com.txusballesteros.domain.model.Note;
import com.txusballesteros.domain.model.TextNote;
import com.txusballesteros.presentation.CreateNotePresenter;
import com.txusballesteros.view.di.DaggerViewComponent;
import com.txusballesteros.view.di.ViewModule;
import javax.inject.Inject;

public class CreateTextNoteFragment extends AbsFragment implements CreateNotePresenter.View {
  @BindView(R.id.title) EditText titleView;
  @BindView(R.id.description) EditText descriptionView;
  @Inject CreateNotePresenter presenter;

  public static Fragment newInstance() {
    return new CreateTextNoteFragment();
  }

  @Override
  void onRequestInjection(ApplicationComponent applicationComponent) {
    DaggerViewComponent.builder()
      .applicationComponent(applicationComponent)
      .viewModule(new ViewModule(this))
      .build()
      .inject(this);
  }

  @Override
  public void onInitializeToolbar() {
    getToolbar().setDisplayHomeAsUpEnabled(true);
    getToolbar().setHomeButtonEnabled(true);
  }

  @Override
  int onRequestLayoutResourceId() {
    return R.layout.fragment_create_text_note;
  }

  @Override
  protected int onRequestMenuResourceId() {
    return R.menu.create_new_note_menu;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    boolean result = true;
    switch(item.getItemId()) {
      case R.id.save:
        createNote();
        break;
      case android.R.id.home:
        closeView();
        break;
      default:
        result = super.onOptionsItemSelected(item);
        break;
    }
    return result;
  }

  private void createNote() {
    final Note note = new TextNote.Builder()
                      .setTitle(titleView.getText().toString())
                      .setDescription(descriptionView.getText().toString())
                      .build();
    presenter.onCreateNote(note);
  }

  @Override
  public void renderTitleRequiredMessage() {
    titleView.requestFocus();
    Toast.makeText(getActivity(), R.string.message_title_required, Toast.LENGTH_LONG).show();
  }

  @Override
  public void closeView() {
    getActivity().finish();
  }
}
