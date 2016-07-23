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
import com.txusballesteros.labs.domain.model.ImageNote;
import com.txusballesteros.labs.domain.model.Note;
import com.txusballesteros.labs.domain.model.TaskListNote;
import com.txusballesteros.labs.domain.model.TextNote;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NoteDataModelMapper {
  private final TextNoteDataModelMapper textNoteDataModelMapper;
  private final TasksListNoteDataModelMapper tasksListNoteDataModelMapper;
  private final ImageNoteDataModelMapper imageNoteDataModelMapper;

  @Inject
  public NoteDataModelMapper(TextNoteDataModelMapper textNoteDataModelMapper,
                             TasksListNoteDataModelMapper tasksListNoteDataModelMapper,
                             ImageNoteDataModelMapper imageNoteDataModelMapper) {
    this.textNoteDataModelMapper = textNoteDataModelMapper;
    this.tasksListNoteDataModelMapper = tasksListNoteDataModelMapper;
    this.imageNoteDataModelMapper = imageNoteDataModelMapper;
  }

  public List<Note> map(List<NoteDataModel> source) {
    List<Note> result = new ArrayList<>(source.size());
    for(NoteDataModel noteDataModel : source) {
      result.add(map(noteDataModel));
    }
    return result;
  }

  public NoteDataModel update(@NonNull NoteDataModel source, long id) {
    NoteDataModel result;
    switch(source.getType()) {
      case TASK_LIST:
        result = tasksListNoteDataModelMapper.update((TaskListNoteDataModel) source, id);
        break;
      case IMAGE:
        result = imageNoteDataModelMapper.update((ImageNoteDataModel) source, id);
        break;
      default:
        result = textNoteDataModelMapper.update((TextNoteDataModel) source, id);
        break;
    }
    return result;
  }

  public Note map(@NonNull NoteDataModel source) {
    Note result;
    switch(source.getType()) {
      case TASK_LIST:
        result = tasksListNoteDataModelMapper.map((TaskListNoteDataModel) source);
        break;
      case IMAGE:
        result = imageNoteDataModelMapper.map((ImageNoteDataModel) source);
        break;
      default:
        result = textNoteDataModelMapper.map((TextNoteDataModel) source);
        break;
    }
    return result;
  }

  public NoteDataModel map(@NonNull Note source) {
    NoteDataModel result;
    switch(source.getType()) {
      case TASK_LIST:
        result = tasksListNoteDataModelMapper.map((TaskListNote) source);
        break;
      case IMAGE:
        result = imageNoteDataModelMapper.map((ImageNote) source);
        break;
      default:
        result = textNoteDataModelMapper.map((TextNote) source);
        break;
    }
    return result;
  }
}
