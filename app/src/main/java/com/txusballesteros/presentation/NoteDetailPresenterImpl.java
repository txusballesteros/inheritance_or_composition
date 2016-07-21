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
package com.txusballesteros.presentation;

import android.support.annotation.NonNull;
import com.txusballesteros.domain.interactor.GetNoteByIdUseCase;
import com.txusballesteros.domain.model.ImageNote;
import com.txusballesteros.domain.model.Note;
import com.txusballesteros.domain.model.NoteType;
import com.txusballesteros.domain.model.TaskListNote;
import com.txusballesteros.domain.model.TextNote;
import javax.inject.Inject;

public class NoteDetailPresenterImpl implements NoteDetailPresenter {
  private final View view;
  private final GetNoteByIdUseCase getNoteByIdUseCase;
  private Note note;

  @Inject
  public NoteDetailPresenterImpl(NoteDetailPresenter.View view,
                                 GetNoteByIdUseCase getNoteByIdUseCase) {
    this.view = view;
    this.getNoteByIdUseCase = getNoteByIdUseCase;
  }

  @Override
  public void onAttach(long noteId, NoteType type) {
    getNoteByIdUseCase.execute(noteId, new GetNoteByIdUseCase.Callback() {
      @Override
      public void onNoteReady(@NonNull Note note) {
        NoteDetailPresenterImpl.this.note = note;
        displayNoteDetail();
      }
    });
  }

  @Override
  public void onRequestDeleteNote() {
    view.closeView();
  }

  private void displayNoteDetail() {
    switch(note.getType()) {
      case TEXT:
        view.showTextNoteDetail((TextNote) note);
        break;
      case TASK_LIST:
        view.showTasksListNoteDetail((TaskListNote) note);
        break;
      case IMAGE:
        view.showImageNoteDetail((ImageNote) note);
        break;
    }
  }
}