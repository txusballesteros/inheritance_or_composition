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
package com.txusballesteros.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.txusballesteros.R;
import com.txusballesteros.di.ApplicationComponent;
import com.txusballesteros.domain.model.NoteType;
import com.txusballesteros.presentation.NoteDetailPresenter;
import com.txusballesteros.view.di.DaggerViewComponent;
import com.txusballesteros.view.di.ViewModule;
import javax.inject.Inject;

public class NoteDetailFragment extends AbsFragment implements NoteDetailPresenter.View {
  public static final String EXTRA_NOTE_TYPE = "note:type";
  public static final String EXTRA_NOTE_ID = "note:id";
  @Inject NoteDetailPresenter presenter;

  public static Fragment newInstance(long noteId, NoteType type) {
    Bundle arguments = new Bundle(2);
    arguments.putLong(EXTRA_NOTE_ID, noteId);
    arguments.putInt(EXTRA_NOTE_TYPE, type.ordinal());
    Fragment result = new NoteDetailFragment();
    result.setArguments(arguments);
    return result;
  }

  @Override
  void onRequestInjection(ApplicationComponent applicationComponent) {
    DaggerViewComponent.builder()
      .applicationComponent(applicationComponent)
      .viewModule(new ViewModule(this))
      .build()
      .inject(this);
  }

  @Override
  int onRequestLayoutResourceId() {
    return R.layout.fragment_note_detail;
  }

  @Override
  public void onPresenterShouldBeAttached() {
    long noteId = getNoteId();
    NoteType noteType = getNoteType();
    presenter.onAttach(noteId, noteType);
  }

  private long getNoteId() {
    return getArguments().getLong(EXTRA_NOTE_ID);
  }

  private NoteType getNoteType() {
    int noteType = getArguments().getInt(EXTRA_NOTE_TYPE);
    return NoteType.fromInt(noteType);
  }
}
