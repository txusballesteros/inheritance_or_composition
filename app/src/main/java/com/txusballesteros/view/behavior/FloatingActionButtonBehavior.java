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
package com.txusballesteros.view.behavior;

import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.txusballesteros.R;

public class FloatingActionButtonBehavior extends Behavior {
  private final View.OnClickListener listener;

  public FloatingActionButtonBehavior(View.OnClickListener listener) {
    this.listener = listener;
  }

  @Override
  protected int onRequestPlaceHolderId() {
    return R.id.floating_button_place_holder;
  }

  @Override
  protected int onRequestLayoutResourceId() {
    return R.layout.behavior_floating_button;
  }

  @Override
  protected void onBehaviorReady(View rootView, View view) {
    ButterKnife.bind(this, view);
  }

  @OnClick(R.id.fab)
  protected void onFabClick(View view) {
    listener.onClick(view);
  }
}
