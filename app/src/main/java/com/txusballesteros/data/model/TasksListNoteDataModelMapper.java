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
package com.txusballesteros.data.model;

import com.txusballesteros.domain.model.Task;
import com.txusballesteros.domain.model.TaskListNote;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TasksListNoteDataModelMapper {
  private final TaskDataModelMapper taskDataModelMapper;

  @Inject
  public TasksListNoteDataModelMapper(TaskDataModelMapper taskDataModelMapper) {
    this.taskDataModelMapper = taskDataModelMapper;
  }

  public TaskListNoteDataModel update(TaskListNoteDataModel source, long id) {
    final TaskListNoteDataModel.Builder builder = new TaskListNoteDataModel.Builder();
    builder.setId(id);
    builder.setTitle(source.getTitle());
    builder.setDescription(source.getDescription());
    builder.setTasks(source.getTasks());
    return (TaskListNoteDataModel) builder.build();
  }

  public TaskListNote map(TaskListNoteDataModel source) {
    final List<Task> tasks = taskDataModelMapper.map(source.getTasks());
    final TaskListNote.Builder builder = new TaskListNote.Builder();
    builder.setId(source.getId());
    builder.setTitle(source.getTitle());
    builder.setDescription(source.getDescription());
    builder.setTasks(tasks);
    return (TaskListNote) builder.build();
  }

  public TaskListNoteDataModel map(TaskListNote source) {
    final List<TaskDataModel> tasks = taskDataModelMapper.mapToDomain(source.getTasks());
    final TaskListNoteDataModel.Builder builder = new TaskListNoteDataModel.Builder();
    builder.setId(source.getId());
    builder.setTitle(source.getTitle());
    builder.setDescription(source.getDescription());
    builder.setTasks(tasks);
    return (TaskListNoteDataModel) builder.build();
  }
}
