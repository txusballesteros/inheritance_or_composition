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

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.widget.TextView;
import butterknife.BindView;
import com.txusballesteros.R;
import com.txusballesteros.di.ApplicationComponent;
import com.txusballesteros.domain.model.Note;
import com.txusballesteros.instrumentation.ImageDownloader;
import com.txusballesteros.presentation.NoteDetailPresenter;
import com.txusballesteros.view.di.DaggerViewComponent;
import com.txusballesteros.view.di.ViewModule;
import javax.inject.Inject;

public class TextNoteDetailFragment extends AbsFragment implements NoteDetailPresenter.View {
  public static final String EXTRA_NOTE_ID = "note:id";
  @Inject NoteDetailPresenter presenter;
  @Inject ImageDownloader imageDownloader;
  @BindView(R.id.title) TextView titleView;
  @BindView(R.id.description) TextView descriptionView;

  public static Fragment newInstance(long noteId) {
    Bundle arguments = new Bundle(1);
    arguments.putLong(EXTRA_NOTE_ID, noteId);
    Fragment result = new TextNoteDetailFragment();
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
  public void onInitializeToolbar() {
    getToolbar().setDisplayHomeAsUpEnabled(true);
    getToolbar().setHomeButtonEnabled(true);
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
  public void onPresenterShouldBeAttached() {
    presenter.onAttach(getNoteId());
  }

  private long getNoteId() {
    return getArguments().getLong(EXTRA_NOTE_ID);
  }

  @Override
  int onRequestLayoutResourceId() {
    return R.layout.fragment_text_note_detail;
  }

  @Override
  public void renderNote(Note note) {
    renderTitle(note);
    renderDescription(note);
  }

  protected void renderTitle(Note note) {
    titleView.setText(note.getTitle());
  }

  protected void renderDescription(Note note) {
    descriptionView.setText(note.getDescription());
  }

  @Override
  public void closeView() {
    getActivity().finish();
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
}
