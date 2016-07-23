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
package com.txusballesteros.labs.data.notes.datasource;

import android.util.LongSparseArray;
import com.txusballesteros.labs.data.model.ImageNoteDataModel;
import com.txusballesteros.labs.data.model.NoteDataModel;
import com.txusballesteros.labs.data.model.NoteDataModelMapper;
import com.txusballesteros.labs.data.model.TaskDataModel;
import com.txusballesteros.labs.data.model.TaskListNoteDataModel;
import com.txusballesteros.labs.data.model.TextNoteDataModel;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class NotesInMemoryLocalDataSource implements NotesLocalDataSource {
  private final NoteDataModelMapper mapper;
  private LongSparseArray<NoteDataModel> dataSet;

  @Inject
  public NotesInMemoryLocalDataSource(NoteDataModelMapper mapper) {
    this.mapper = mapper;
    initializeAndFetchDataSet();
  }

  private void initializeAndFetchDataSet() {
    dataSet = new LongSparseArray<>();
    buildTextNote(1, "Learning Clean Code & SOLID", "Which is the best way to learn Clean Code and SOLID?");
    buildTasksListNote(2, "Pokemon GO", "Pokemons on my Pokédex", buildPokemonsList());
    buildImageNote(3, "My Best Picture...", "This is my favorite picture.", "http://lorempixel.com/400/400/nature/");
    buildTextNote(4, "This is really awesome", "This technique of programing is really nice. Remember learn more about that in the future and if you want to have more info about that, please feel free to contact with me.");
    buildImageNote(5, "I love this city", "Remembering my travel to this great city.", "http://lorempixel.com/400/400/city/");
  }

  private List<TaskDataModel> buildPokemonsList() {
    List<TaskDataModel> result = new ArrayList<>();
    result.add(new TaskDataModel.Builder().setOrder(1).setTitle("Pikachu").setIsDone(true).build());
    result.add(new TaskDataModel.Builder().setOrder(2).setTitle("Bulbasaur").setIsDone(true).build());
    result.add(new TaskDataModel.Builder().setOrder(3).setTitle("Ivysaur").setIsDone(false).build());
    result.add(new TaskDataModel.Builder().setOrder(4).setTitle("Venusaur").setIsDone(true).build());
    result.add(new TaskDataModel.Builder().setOrder(5).setTitle("Blastoise").setIsDone(false).build());
    return result;
  }

  private void buildTextNote(long id, String title, String description) {
    final TextNoteDataModel.Builder builder = new TextNoteDataModel.Builder();
    builder.setId(id);
    builder.setTitle(title);
    builder.setDescription(description);
    fetchNote(builder.build());
  }

  private void buildTasksListNote(long id, String title, String description, List<TaskDataModel> tasks) {
    final TaskListNoteDataModel.Builder builder = new TaskListNoteDataModel.Builder();
    builder.setId(id);
    builder.setTitle(title);
    builder.setDescription(description);
    builder.setTasks(tasks);
    fetchNote(builder.build());
  }

  private void buildImageNote(long id, String title, String description, String imageUrl) {
    final ImageNoteDataModel.Builder builder = new ImageNoteDataModel.Builder();
    builder.setId(id);
    builder.setTitle(title);
    builder.setDescription(description);
    builder.setImageUrl(imageUrl);
    fetchNote(builder.build());
  }

  private void fetchNote(NoteDataModel note) {
    dataSet.put(note.getId(), note);
  }

  @Override
  public void storeNote(NoteDataModel note) {
    long noteId = calculateNoteId();
    NoteDataModel updatedNote = mapper.update(note, noteId);
    dataSet.put(updatedNote.getId(), updatedNote);
  }

  @Override
  public void deleteNote(long id) {
    dataSet.remove(id);
  }

  @Override
  public List<NoteDataModel> getNotes() {
    List<NoteDataModel> result = new ArrayList<>(dataSet.size());
    for(int index = 0; index < dataSet.size(); index++) {
      result.add(dataSet.valueAt(index));
    }
    return result;
  }

  @Override
  public NoteDataModel getNotesById(long id) {
    NoteDataModel result = dataSet.get(id);
    return result;
  }

  private long calculateNoteId() {
    long maximumId = 0;
    for(int position = 0; position < dataSet.size(); position++) {
      long noteId = dataSet.valueAt(position).getId();
      if (noteId > maximumId) {
        maximumId = noteId;
      }
    }
    return maximumId + 1;
  }
}
