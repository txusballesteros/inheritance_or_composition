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

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.LongSparseArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.txusballesteros.R;
import com.txusballesteros.domain.model.ImageNote;
import com.txusballesteros.domain.model.Note;
import com.txusballesteros.domain.model.NoteType;
import com.txusballesteros.domain.model.Task;
import com.txusballesteros.domain.model.TaskListNote;
import com.txusballesteros.instrumentation.ImageDownloader;
import java.util.ArrayList;
import java.util.List;

public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.ViewHolder> {
  private final LongSparseArray<Note> dataSet;
  private final ImageDownloader imageDownloader;
  private OnNoteClickListener listener;

  public NotesListAdapter(ImageDownloader imageDownloader) {
    this.imageDownloader = imageDownloader;
    dataSet = new LongSparseArray<>();
  }

  public void setOnNoteClickListener(OnNoteClickListener listener) {
    this.listener = listener;
  }

  public void clear() {
    dataSet.clear();
  }

  public void addAll(List<Note> notes) {
    for(Note note : notes) {
      dataSet.put(note.getId(), note);
    }
    notifyDataSetChanged();
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
      notifyItemInserted(adapterPosition);
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
      notifyItemRemoved(adapterPosition);
    }
  }

  private LongSparseArray<Note> toMap(List<Note> notes) {
    LongSparseArray result = new LongSparseArray(notes.size());
    for(Note note : notes) {
      result.put(note.getId(),note);
    }
    return result;
  }

  @Override
  public int getItemViewType(int position) {
    Note note = dataSet.valueAt(position);
    return note.getType().ordinal();
  }

  @Override
  public NotesListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    NoteType type = NoteType.fromInt(viewType);
    int viewHolderLayoutResourceId;
    switch(type) {
      case TASK_LIST:
        viewHolderLayoutResourceId = R.layout.item_note_tasks_list;
        break;
      case IMAGE:
        viewHolderLayoutResourceId = R.layout.item_note_image;
        break;
      default:
        viewHolderLayoutResourceId = R.layout.item_note_text;
        break;
    }
    View holderView = LayoutInflater.from(parent.getContext()).inflate(viewHolderLayoutResourceId, parent, false);
    return new ViewHolder(holderView, this);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    Note note = dataSet.valueAt(position);
    holder.render(note);
  }

  @Override
  public int getItemCount() {
    return dataSet.size();
  }

  void onViewHolderClick(View sharedView, int position) {
    if (listener != null) {
      Note note = dataSet.valueAt(position);
      listener.onNoteClick(sharedView, note);
    }
  }

  public interface OnNoteClickListener {
    void onNoteClick(View sharedView, @NonNull Note note);
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    private final View itemView;
    private final NotesListAdapter adapter;
    @Nullable @BindView(R.id.title)  TextView titleView;
    @Nullable @BindView(R.id.description) TextView descriptionView;
    @Nullable @BindView(R.id.image) ImageView imageView;
    @Nullable @BindView(R.id.tasks_holder) ViewGroup tasksHolderView;

    public ViewHolder(View itemView, final NotesListAdapter adapter) {
      super(itemView);
      this.itemView = itemView;
      this.adapter = adapter;
      ButterKnife.bind(this, itemView);
      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          int position = getAdapterPosition();
          View sharedView = getSharedView();
          adapter.onViewHolderClick(sharedView, position);
        }
      });
    }

    public void render(Note note) {
      switch(note.getType()) {
        case TEXT:
          renderTextNote(note);
          break;
        case TASK_LIST:
          renderTasksListNote((TaskListNote) note);
          break;
        case IMAGE:
          renderImageNote((ImageNote) note);
          break;
      }
    }

    public View getSharedView() {
      return null;
    }

    private void renderTextNote(Note note) {
      titleView.setText(note.getTitle());
      descriptionView.setText(note.getDescription());
    }

    private void renderImageNote(ImageNote note) {
      renderTextNote(note);
      adapter.imageDownloader.downloadImage(note.getImageUrl(), imageView);
    }

    private void renderTasksListNote(TaskListNote note) {
      renderTextNote(note);
      renderTasks(note.getTasks());
    }

    private void renderTasks(@NonNull List<Task> tasks) {
      tasksHolderView.removeAllViews();
      for(Task task : tasks) {
        renderTask(task);
      }
    }

    @SuppressWarnings("deprecated")
    private void renderTask(Task task) {
      final Context context = itemView.getContext();
      CheckBox taskView = new CheckBox(context);
      taskView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                             ViewGroup.LayoutParams.WRAP_CONTENT));
      taskView.setTextAppearance(context, R.style.task);
      taskView.setText(task.getTitle());
      taskView.setChecked(task.isDone());
      tasksHolderView.addView(taskView);
    }
  }
}
