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
package com.txusballesteros.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.txusballesteros.domain.model.ImageNote;
import com.txusballesteros.domain.model.Note;
import com.txusballesteros.domain.model.NoteType;
import com.txusballesteros.domain.model.TaskListNote;
import com.txusballesteros.domain.model.TextNote;
import com.txusballesteros.instrumentation.ImageDownloader;
import com.txusballesteros.view.adapter.holder.ImageNoteAdapterViewHolder;
import com.txusballesteros.view.adapter.holder.NoteAdapterViewHolder;
import com.txusballesteros.view.adapter.holder.NoteAdapterViewHolderFactory;
import com.txusballesteros.view.adapter.holder.TasksListNoteAdapterViewHolder;
import com.txusballesteros.view.adapter.holder.TextNoteAdapterViewHolder;
import java.util.ArrayList;
import java.util.List;

public class NotesListAdapter extends RecyclerView.Adapter<NoteAdapterViewHolder>
                              implements NoteAdapterViewHolder.OnViewHolderClickListener {
  private final List<Note> dataSet;
  private final ImageDownloader imageDownloader;
  private OnNoteClickListener listener;

  public NotesListAdapter(ImageDownloader imageDownloader) {
    this.imageDownloader = imageDownloader;
    dataSet = new ArrayList<>();
  }

  public void setOnNoteClickListener(OnNoteClickListener listener) {
    this.listener = listener;
  }

  public void clear() {
    dataSet.clear();
  }

  public void addAll(List<Note> notes) {
    dataSet.addAll(notes);
  }

  @Override
  public int getItemViewType(int position) {
    Note note = dataSet.get(position);
    return note.getType().ordinal();
  }

  @Override
  public NoteAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return NoteAdapterViewHolderFactory.build(parent, NoteType.fromInt(viewType), this);
  }

  @Override
  public void onBindViewHolder(NoteAdapterViewHolder viewHolder, int position) {
    Note note = dataSet.get(position);
    switch(note.getType()) {
      case TEXT:
        renderTextNote((TextNote) note, (TextNoteAdapterViewHolder) viewHolder);
        break;
      case IMAGE:
        renderImageNote((ImageNote) note, (ImageNoteAdapterViewHolder) viewHolder);
        break;
      case TASK_LIST:
        renderTasksListNote((TaskListNote) note, (TasksListNoteAdapterViewHolder) viewHolder);
        break;
    }
  }

  private void renderTextNote(TextNote note, TextNoteAdapterViewHolder viewHolder) {
    viewHolder.renderTitle(note.getTitle());
    viewHolder.renderDescription(note.getDescription());
  }

  private void renderTasksListNote(TaskListNote note, TasksListNoteAdapterViewHolder viewHolder) {
    viewHolder.renderTitle(note.getTitle());
    viewHolder.renderDescription(note.getDescription());
    viewHolder.renderTasks(note.getTasks());
  }

  private void renderImageNote(ImageNote note, ImageNoteAdapterViewHolder viewHolder) {
    viewHolder.renderTitle(note.getTitle());
    viewHolder.renderDescription(note.getDescription());
    viewHolder.renderImage(imageDownloader, note.getImageUrl());
  }

  @Override
  public int getItemCount() {
    return dataSet.size();
  }

  @Override
  public void onViewHolderClick(RecyclerView.ViewHolder holder, View sharedView, int position) {
    if (listener != null) {
      Note note = dataSet.get(position);
      listener.onNoteClick(sharedView, note);
    }
  }

  public interface OnNoteClickListener {
    void onNoteClick(View sharedView, @NonNull Note note);
  }
}
