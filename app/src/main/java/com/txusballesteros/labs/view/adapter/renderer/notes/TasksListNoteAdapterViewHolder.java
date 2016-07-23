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
package com.txusballesteros.labs.view.adapter.renderer.notes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import butterknife.BindView;
import com.txusballesteros.labs.R;
import com.txusballesteros.labs.domain.model.Note;
import com.txusballesteros.labs.domain.model.Task;
import com.txusballesteros.labs.domain.model.TaskListNote;

public class TasksListNoteAdapterViewHolder extends TextNoteAdapterViewHolder {
  @BindView(R.id.tasks_holder) ViewGroup tasksHolderView;

  public TasksListNoteAdapterViewHolder(@NonNull View view, @NonNull OnViewHolderClickListener listener) {
    super(view, listener);
  }

  @Override
  public void render(@NonNull Note note) {
    super.render(note);
    renderTasks(note);
  }

  private void renderTasks(@NonNull Note note) {
    if (note instanceof TaskListNote) {
      final TaskListNote tasksListNote = (TaskListNote) note;
      tasksHolderView.removeAllViews();
      for(Task task : tasksListNote.getTasks()) {
        renderTask(task);
      }
    }
  }

  @SuppressWarnings("deprecated")
  private void renderTask(Task task) {
    final Context context = getView().getContext();
    CheckBox taskView = new CheckBox(context);
    taskView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                           ViewGroup.LayoutParams.WRAP_CONTENT));
    taskView.setTextAppearance(context, R.style.task);
    taskView.setText(task.getTitle());
    taskView.setChecked(task.isDone());
    tasksHolderView.addView(taskView);
  }
}
