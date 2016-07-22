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
package com.txusballesteros.view.adapter.renderer.notes;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.txusballesteros.R;
import com.txusballesteros.domain.model.Note;

public class TextNoteAdapterViewHolder extends NoteAdapterViewHolder {
  @BindView(R.id.title) TextView nameView;
  @BindView(R.id.description) TextView descriptionView;

  public TextNoteAdapterViewHolder(@NonNull View view, @NonNull OnViewHolderClickListener listener) {
    super(view, listener);
  }

  @Override
  public void render(@NonNull Note note) {
    renderTitle(note.getTitle());
    renderDescription(note.getDescription());
  }

  private void renderTitle(@NonNull String name) {
    nameView.setText(name);
  }

  private void renderDescription(@NonNull String description) {
    descriptionView.setText(description);
  }
}
