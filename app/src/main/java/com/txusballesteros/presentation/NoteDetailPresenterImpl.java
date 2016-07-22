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
import com.txusballesteros.domain.interactor.DeleteNoteUseCase;
import com.txusballesteros.domain.interactor.GetNoteByIdUseCase;
import com.txusballesteros.domain.model.Note;
import javax.inject.Inject;

public class NoteDetailPresenterImpl implements NoteDetailPresenter {
  private final View view;
  private final GetNoteByIdUseCase getNoteByIdUseCase;
  private final DeleteNoteUseCase deleteNoteUseCase;
  private Note note;

  @Inject
  public NoteDetailPresenterImpl(NoteDetailPresenter.View view,
                                 GetNoteByIdUseCase getNoteByIdUseCase,
                                 DeleteNoteUseCase deleteNoteUseCase) {
    this.view = view;
    this.getNoteByIdUseCase = getNoteByIdUseCase;
    this.deleteNoteUseCase = deleteNoteUseCase;
  }

  @Override
  public void onAttach(long noteId) {
    getNoteByIdUseCase.execute(noteId, new GetNoteByIdUseCase.Callback() {
      @Override
      public void onNoteReady(@NonNull Note note) {
        NoteDetailPresenterImpl.this.note = note;
        view.renderNote(note);
      }
    });
  }

  @Override
  public void onRequestDeleteNote() {
    view.askToConfirmDeletion();
  }

  @Override
  public void onDeleteConfirmed() {
    deleteNoteUseCase.execute(note.getId(), new DeleteNoteUseCase.Callback() {
      @Override
      public void onNoteDeleted() {
        view.closeView();
      }
    });
  }
}
