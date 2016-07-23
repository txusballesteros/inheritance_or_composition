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
package com.txusballesteros.labs.data.model;

import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

public final class TaskListNoteDataModel extends NoteDataModel {
  private final List<TaskDataModel> taskDataModels;

  private TaskListNoteDataModel(Builder builder) {
    super(builder);
    this.taskDataModels = builder.tasks;
  }

  public List<TaskDataModel> getTasks() {
    return taskDataModels;
  }

  @Override
  public NoteTypeDataModel getType() {
    return NoteTypeDataModel.TASK_LIST;
  }

  public static class Builder extends NoteDataModel.Builder {
    private List<TaskDataModel> tasks = new ArrayList<>();

    public Builder setTasks(@NonNull List<TaskDataModel> tasks) {
      this.tasks = tasks;
      return this;
    }

    @Override
    public NoteDataModel build() {
      return new TaskListNoteDataModel(this);
    }
  }
}
