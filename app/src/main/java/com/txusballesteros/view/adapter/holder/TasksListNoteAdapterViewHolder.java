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
package com.txusballesteros.view.adapter.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import butterknife.BindView;
import com.txusballesteros.R;
import com.txusballesteros.domain.model.Task;
import java.util.List;

public class TasksListNoteAdapterViewHolder extends NoteAdapterViewHolder {
  @BindView(R.id.tasks_holder) ViewGroup tasksHolderView;

  public TasksListNoteAdapterViewHolder(@NonNull View view, @NonNull OnViewHolderClickListener listener) {
    super(view, listener);
  }

  public void renderTasks(@NonNull List<Task> tasks) {
    tasksHolderView.removeAllViews();
    for(Task task : tasks) {
      renderTask(task);
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
