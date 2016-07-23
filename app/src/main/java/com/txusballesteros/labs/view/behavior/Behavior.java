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
package com.txusballesteros.labs.view.behavior;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewStub;

public abstract class Behavior {
  public void inject(@NonNull Activity activity) {
    inject(activity.getWindow().getDecorView().getRootView());
  }

  public void inject(@NonNull View rootView) {
    if (rootView != null) {
      int placeHolderViewId = onRequestPlaceHolderId();
      ViewStub placeHolder = findPlaceHolderView(rootView, placeHolderViewId);
      if (placeHolder != null) {
        attachBehaviorLayout(rootView, placeHolder);
      }
    }
  }

  @Nullable
  private ViewStub findPlaceHolderView(View rootView, int placeHolderId) {
    ViewStub result = null;
    View view = rootView.findViewById(placeHolderId);
    if (view instanceof ViewStub) {
      result = (ViewStub) view;
    }
    return result;
  }

  private void attachBehaviorLayout(final View rootView, ViewStub placeHolderView) {
    int behaviorLayoutResourceId = onRequestLayoutResourceId();
    placeHolderView.setLayoutResource(behaviorLayoutResourceId);
    placeHolderView.setOnInflateListener(new ViewStub.OnInflateListener() {
      @Override public void onInflate(ViewStub stub, View view) {
        stub.setVisibility(View.VISIBLE);
        onBehaviorReady(rootView, view);
      }
    });
    placeHolderView.inflate();
  }

  @IdRes protected abstract int onRequestPlaceHolderId();

  @LayoutRes protected abstract int onRequestLayoutResourceId();

  protected void onBehaviorReady(View rootView, View view) { }
}
