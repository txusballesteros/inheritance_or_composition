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

import android.support.v4.util.LongSparseArray;
import android.support.v7.widget.RecyclerView;
import com.txusballesteros.labs.domain.model.Note;
import java.util.ArrayList;
import java.util.List;

public class NotesListAdapterCache {
  private final LongSparseArray<Note> dataSet;
  private final RecyclerView.Adapter adapter;

  public NotesListAdapterCache(RecyclerView.Adapter adapter) {
    this.adapter = adapter;
    this.dataSet = new LongSparseArray<>();
  }

  public void clear() {
    dataSet.clear();
  }

  public void addAll(List<Note> notes) {
    for(Note note : notes) {
      dataSet.put(note.getId(), note);
    }
    adapter.notifyDataSetChanged();
  }

  public int size() {
    return dataSet.size();
  }

  public Note get(int position) {
    return dataSet.valueAt(position);
  }

  public void update(List<Note> notes) {
    insertNewNotes(notes);
    removeNotes(notes);
  }

  private void insertNewNotes(List<Note> notes) {
    List<Note> notesToBeInserted = new ArrayList<>();
    for(Note note : notes) {
      if (dataSet.indexOfKey(note.getId()) < 0) {
        notesToBeInserted.add(note);
      }
    }
    for(Note note : notesToBeInserted) {
      dataSet.put(note.getId(), note);
      int adapterPosition = dataSet.indexOfValue(note);
      adapter.notifyItemInserted(adapterPosition);
    }
  }

  private void removeNotes(List<Note> notes) {
    List<Note> notesToBeDeleted = new ArrayList<>();
    LongSparseArray<Note> notesMap = toMap(notes);
    for(int position = 0; position < dataSet.size(); position++) {
      Note cachedNote = dataSet.valueAt(position);
      if (notesMap.indexOfKey(cachedNote.getId()) < 0) {
        notesToBeDeleted.add(cachedNote);
      }
    }
    for(Note note : notesToBeDeleted) {
      int adapterPosition = dataSet.indexOfValue(note);
      dataSet.remove(note.getId());
      adapter.notifyItemRemoved(adapterPosition);
    }
  }

  private LongSparseArray<Note> toMap(List<Note> notes) {
    LongSparseArray result = new LongSparseArray(notes.size());
    for(Note note : notes) {
      result.put(note.getId(),note);
    }
    return result;
  }
}
