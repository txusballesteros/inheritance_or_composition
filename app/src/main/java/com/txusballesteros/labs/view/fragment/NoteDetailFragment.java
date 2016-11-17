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
package com.txusballesteros.labs.view.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.txusballesteros.labs.R;
import com.txusballesteros.labs.di.ApplicationComponent;
import com.txusballesteros.labs.domain.model.ImageNote;
import com.txusballesteros.labs.domain.model.NoteType;
import com.txusballesteros.labs.domain.model.TaskListNote;
import com.txusballesteros.labs.domain.model.TextNote;
import com.txusballesteros.labs.instrumentation.ImageDownloader;
import com.txusballesteros.labs.presentation.NoteDetailPresenter;
import com.txusballesteros.labs.view.di.DaggerViewComponent;
import javax.inject.Inject;

public class NoteDetailFragment extends AbsFragment implements NoteDetailPresenter.View {
  public static final String EXTRA_NOTE_TYPE = "note:type";
  public static final String EXTRA_NOTE_ID = "note:id";
  @Inject NoteDetailPresenter presenter;
  @Inject ImageDownloader imageDownloader;

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
      .build()
      .inject(this);
  }

  @Override
  protected int onRequestLayoutResourceId() {
    return R.layout.fragment_note_detail;
  }

  @Override
  public void onPresenterShouldBeAttached() {
    presenter.onAttach(this);
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }

  @Override
  public void onViewReady() {
    initializeToolbar();
    long noteId = getNoteId();
    NoteType noteType = getNoteType();
    presenter.onRequestNote(noteId, noteType);
  }

  private void initializeToolbar() {
    ActionBar actionBar  = ((AppCompatActivity) getActivity()).getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setHomeButtonEnabled(true);
    }
  }

  @Override
  protected void onPresenterShouldBeDetached() {
    presenter.onDetach();
  }

  @Override
  protected int onRequestMenuResourceId() {
    return R.menu.note_detail_menu;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    boolean result = true;
    switch(item.getItemId()) {
      case R.id.delete:
        presenter.onRequestDeleteNote();
        break;
      case android.R.id.home:
        closeView();
        break;
      default:
        result = super.onOptionsItemSelected(item);
        break;
    }
    return result;
  }

  @Override
  public void closeView() {
    getActivity().finish();
  }

  private long getNoteId() {
    return getArguments().getLong(EXTRA_NOTE_ID);
  }

  private NoteType getNoteType() {
    int noteType = getArguments().getInt(EXTRA_NOTE_TYPE);
    return NoteType.fromInt(noteType);
  }

  @Override
  public void showTextNoteDetail(TextNote note) {
    TextNoteDetailFragment fragment = TextNoteDetailFragment.newInstance();
    fragment.setNote(note);
    showContent(fragment);
  }

  @Override
  public void showTasksListNoteDetail(TaskListNote note) {
    TasksListNoteDetailFragment fragment = TasksListNoteDetailFragment.newInstance();
    fragment.setNote(note);
    showContent(fragment);
  }

  @Override
  public void showImageNoteDetail(ImageNote note) {
    ImageNoteDetailFragment fragment = ImageNoteDetailFragment.newInstance();
    fragment.setImageDownloader(imageDownloader);
    fragment.setNote(note);
    showContent(fragment);
  }

  @Override
  public void askToConfirmDeletion() {
    new AlertDialog.Builder(getActivity())
          .setCancelable(true)
          .setMessage(R.string.message_confirm_deletion)
          .setPositiveButton(R.string.button_yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
              presenter.onDeleteConfirmed();
            }
          })
          .setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
              dialog.dismiss();
            }
          })
          .create()
          .show();
  }

  private void showContent(Fragment content) {
    getActivity().getSupportFragmentManager()
        .beginTransaction()
        .add(R.id.note_detail_place_holder, content)
        .commit();
  }
}
