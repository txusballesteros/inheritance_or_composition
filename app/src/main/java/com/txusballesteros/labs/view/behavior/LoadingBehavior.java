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

import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.txusballesteros.labs.R;

public class LoadingBehavior extends Behavior {
  @BindView(R.id.loading) View loadingView;
  @BindView(R.id.content_place_holder) View contentView;

  @Override
  protected int onRequestPlaceHolderId() {
    return R.id.loading_place_holder;
  }

  @Override
  protected int onRequestLayoutResourceId() {
    return R.layout.behavior_loading;
  }

  @Override
  protected void onBehaviorReady(View rootView, View view) {
    ButterKnife.bind(this, rootView);
  }

  public void showLoading() {
    contentView.setVisibility(View.GONE);
    loadingView.setVisibility(View.VISIBLE);
  }

  public void hideLoading() {
    contentView.setVisibility(View.VISIBLE);
    loadingView.setVisibility(View.GONE);
  }
}
