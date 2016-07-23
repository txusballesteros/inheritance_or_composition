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

import com.txusballesteros.labs.domain.model.Task;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TaskDataModelMapper {
  @Inject
  public TaskDataModelMapper() { }

  public List<Task> map(List<TaskDataModel> source) {
    List<Task> result = new ArrayList<>(source.size());
    for(TaskDataModel task : source) {
      result.add(map(task));
    }
    return result;
  }

  public List<TaskDataModel> mapToDomain(List<Task> source) {
    List<TaskDataModel> result = new ArrayList<>(source.size());
    for(Task task : source) {
      result.add(map(task));
    }
    return result;
  }

  public Task map(TaskDataModel source) {
    return new Task.Builder()
           .setOrder(source.getOrder())
           .setTitle(source.getTitle())
           .setIsDone(source.isDone())
           .build();
  }

  public TaskDataModel map(Task source) {
    return new TaskDataModel.Builder()
           .setOrder(source.getOrder())
           .setTitle(source.getTitle())
           .setIsDone(source.isDone())
           .build();
  }
}
