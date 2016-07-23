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
package com.txusballesteros.labs.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.txusballesteros.labs.domain.model.Note;
import com.txusballesteros.labs.domain.model.NoteType;
import com.txusballesteros.labs.instrumentation.ImageDownloader;
import com.txusballesteros.labs.view.adapter.renderer.notes.NoteAdapterViewHolder;
import com.txusballesteros.labs.view.adapter.renderer.notes.NoteAdapterViewHolderFactory;
import java.util.List;

public class NotesListAdapter extends RecyclerView.Adapter<NoteAdapterViewHolder>
                              implements NoteAdapterViewHolder.OnViewHolderClickListener {
  private final ImageDownloader imageDownloader;
  private final NotesListAdapterCache cache;
  private OnNoteClickListener listener;

  public NotesListAdapter(ImageDownloader imageDownloader) {
    this.cache = new NotesListAdapterCache(this);
    this.imageDownloader = imageDownloader;
  }

  public void setOnNoteClickListener(OnNoteClickListener listener) {
    this.listener = listener;
  }

  public void clear() {
    cache.clear();
  }

  public void addAll(List<Note> notes) {
    cache.addAll(notes);
  }

  public void update(List<Note> notes) {
    cache.update(notes);
  }

  @Override
  public int getItemViewType(int position) {
    Note note = cache.get(position);
    return note.getType().ordinal();
  }

  @Override
  public NoteAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return NoteAdapterViewHolderFactory.build(parent, NoteType.fromInt(viewType), imageDownloader, this);
  }

  @Override
  public void onBindViewHolder(NoteAdapterViewHolder viewHolder, int position) {
    Note note = cache.get(position);
    viewHolder.render(note);
  }

  @Override
  public int getItemCount() {
    return cache.size();
  }

  @Override
  public void onViewHolderClick(RecyclerView.ViewHolder holder, View sharedView, int position) {
    if (listener != null) {
      Note note = cache.get(position);
      listener.onNoteClick(sharedView, note);
    }
  }

  public interface OnNoteClickListener {
    void onNoteClick(View sharedView, @NonNull Note note);
  }
}
