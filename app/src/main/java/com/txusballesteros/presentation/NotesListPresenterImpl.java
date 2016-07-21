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

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.txusballesteros.domain.interactor.GetNotesUseCase;
import com.txusballesteros.domain.model.Note;
import com.txusballesteros.domain.model.NoteType;
import com.txusballesteros.navigation.Navigator;
import java.util.List;
import javax.inject.Inject;

public class NotesListPresenterImpl implements NotesListPresenter {
  private final View view;
  private final GetNotesUseCase getNotesUseCase;
  private final Navigator navigator;
  private PresentationMode presentationMode = PresentationMode.LIST;

  @Inject
  public NotesListPresenterImpl(NotesListPresenter.View view,
                                GetNotesUseCase getNotesUseCase,
                                Navigator navigator) {
    this.view = view;
    this.getNotesUseCase = getNotesUseCase;
    this.navigator = navigator;
  }

  @Override
  public void onResume() {
    view.showLoading();
    configurePresentationMode();
    getNotesUseCase.execute(new GetNotesUseCase.Callback() {
      @Override
      public void onActorReady(@NonNull List<Note> notes) {
        view.hideLoading();
        view.renderNotesList(notes);
      }
    });
  }

  private void configurePresentationMode() {
    if (presentationMode == PresentationMode.LIST) {
      view.showPresentationModeList();
    } else {
      view.showPresentationModeGrid();
    }
  }

  @Override
  public void onAddNewNoteClick(@NonNull Context context) {
    navigator.navigateToCreateNewNote(context, NoteType.TEXT);
  }

  @Override
  public void onRequestChangePresentationMode() {
    if (presentationMode == PresentationMode.LIST) {
      presentationMode = PresentationMode.GRID;
      view.showPresentationModeGrid();
    } else {
      presentationMode = PresentationMode.LIST;
      view.showPresentationModeList();
    }
  }

  @Override
  public void onRequestAbout() {

  }

  @Override
  public void onRequestNoteDetail(@NonNull Context context, @NonNull Note note,
                                  @Nullable android.view.View sharedView) {
    navigator.navigateToNoteDetail(context, note, sharedView);
  }
}
