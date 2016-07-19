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
package com.txusballesteros.data.notes.datasource;

import com.txusballesteros.data.model.ImageNoteDataModel;
import com.txusballesteros.data.model.NoteDataModel;
import com.txusballesteros.data.model.TaskDataModel;
import com.txusballesteros.data.model.TaskListNoteDataModel;
import com.txusballesteros.data.model.TextNoteDataModel;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public class NotesInMemoryDataSource implements NotesDataSource {
  private Map<Long, NoteDataModel> dataSet;

  @Inject
  public NotesInMemoryDataSource() {
    initializeAndFetchDataSet();
  }

  private void initializeAndFetchDataSet() {
    dataSet = new LinkedHashMap<>();
    buildTextNote(1, "Learning Clean Code & SOLID", "Which is the best way to learn Clean Code and SOLID?");
    buildTasksListNote(2, "Pokemon GO", "Pokemons on my Pokédex", buildPokemonsList());
    buildImageNote(3, "My Best Picture...", "This is my favorite picture.", "https://goo.gl/tNGwt2");
  }

  private List<TaskDataModel> buildPokemonsList() {
    List<TaskDataModel> result = new ArrayList<>();
    result.add(new TaskDataModel.Builder().setOrder(1).setTitle("Pikachu").setIsDone(true).build());
    result.add(new TaskDataModel.Builder().setOrder(2).setTitle("Bulbasaur").setIsDone(true).build());
    result.add(new TaskDataModel.Builder().setOrder(3).setTitle("Ivysaur").setIsDone(false).build());
    result.add(new TaskDataModel.Builder().setOrder(4).setTitle("Venusaur").setIsDone(false).build());
    result.add(new TaskDataModel.Builder().setOrder(5).setTitle("Blastoise").setIsDone(false).build());
    return result;
  }

  private void buildTextNote(long id, String title, String description) {
    final TextNoteDataModel.Builder builder = new TextNoteDataModel.Builder();
    builder.setId(id);
    builder.setTitle(title);
    builder.setDescription(description);
    storeNote(builder.build());
  }

  private void buildTasksListNote(long id, String title, String description, List<TaskDataModel> tasks) {
    final TaskListNoteDataModel.Builder builder = new TaskListNoteDataModel.Builder();
    builder.setId(id);
    builder.setTitle(title);
    builder.setDescription(description);
    builder.setTasks(tasks);
    storeNote(builder.build());
  }

  private void buildImageNote(long id, String title, String description, String imageUrl) {
    final ImageNoteDataModel.Builder builder = new ImageNoteDataModel.Builder();
    builder.setId(id);
    builder.setTitle(title);
    builder.setDescription(description);
    builder.setImageUrl(imageUrl);
    storeNote(builder.build());
  }

  @Override
  public void storeNote(NoteDataModel note) {
    dataSet.put(note.getId(), note);
  }

  @Override
  public List<NoteDataModel> getNotes() {
    return new ArrayList<>(dataSet.values());
  }

  @Override
  public NoteDataModel getNotesById(long id) {
    NoteDataModel result = dataSet.get(id);
    return result;
  }
}
