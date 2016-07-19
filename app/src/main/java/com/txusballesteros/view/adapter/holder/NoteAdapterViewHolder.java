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
package com.txusballesteros.view.adapter.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.txusballesteros.R;

public class NoteAdapterViewHolder extends RecyclerView.ViewHolder {
  private View rootView;
  @BindView(R.id.title) TextView nameView;
  @BindView(R.id.description) TextView descriptionView;

  protected View getView() {
    return rootView;
  }

  public void renderTitle(@NonNull String name) {
    nameView.setText(name);
  }

  public void renderDescription(@NonNull String description) {
    descriptionView.setText(description);
  }

  public NoteAdapterViewHolder(View view) {
    super(view);
    this.rootView = view;
    ButterKnife.bind(this, view);
  }
}
