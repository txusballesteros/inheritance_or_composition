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
package com.txusballesteros.labs.presentation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.txusballesteros.labs.domain.model.Note;
import java.util.List;

public interface NotesListPresenter extends Presenter<NotesListPresenter.View> {
  void onRequestNotes();
  void onRequestRefreshNotes();
  void onAddNewNoteClick(Context context);
  void onRequestChangePresentationMode();
  void onRequestAbout(@NonNull Context context);
  void onRequestNoteDetail(@NonNull Context context, @NonNull Note note, @Nullable android.view.View sharedView);

  enum PresentationMode {
    LIST,
    GRID
  }

  interface View extends Presenter.View {
    void renderNotesList(List<Note> notes);
    void updateNotesList(List<Note> notes);
    void showLoading();
    void hideLoading();
    void showPresentationModeList();
    void showPresentationModeGrid();
  }
}
