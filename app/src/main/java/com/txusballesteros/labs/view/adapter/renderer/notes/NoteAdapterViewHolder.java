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
package com.txusballesteros.labs.view.adapter.renderer.notes;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.ButterKnife;
import com.txusballesteros.labs.domain.model.Note;

public abstract class NoteAdapterViewHolder extends RecyclerView.ViewHolder {
  private final View rootView;
  private final OnViewHolderClickListener listener;

  protected View getView() {
    return rootView;
  }

  public NoteAdapterViewHolder(@NonNull View view, @NonNull OnViewHolderClickListener listener) {
    super(view);
    this.rootView = view;
    this.listener = listener;
    ButterKnife.bind(this, view);
    initializeListeners();
  }

  private void initializeListeners() {
    rootView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        int position = getAdapterPosition();
        View sharedView = getSharedView();
        listener.onViewHolderClick(NoteAdapterViewHolder.this, sharedView, position);
      }
    });
  }

  @Nullable
  protected View getSharedView() {
    return null;
  }

  public interface OnViewHolderClickListener {
    void onViewHolderClick(RecyclerView.ViewHolder holder, View view, int position);
  }

  public abstract void render(@NonNull Note note);
}
