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
package com.txusballesteros.data.notes.repository;

import com.txusballesteros.data.notes.cache.NotesCachePolicy;
import com.txusballesteros.data.notes.datasource.NotesCloudDataSource;
import com.txusballesteros.data.notes.datasource.NotesLocalDataSource;
import com.txusballesteros.data.model.NoteDataModel;
import com.txusballesteros.data.model.NoteDataModelMapper;
import com.txusballesteros.domain.model.Note;
import com.txusballesteros.domain.repository.NotesRepository;
import java.util.List;
import javax.inject.Inject;

public class NotesRepositoryImpl implements NotesRepository {
  private final NotesCachePolicy cachePolicy;
  private final NotesLocalDataSource localDataSource;
  private final NotesCloudDataSource cloudDataSource;
  private final NoteDataModelMapper mapper;

  @Inject
  public NotesRepositoryImpl(NotesCachePolicy cachePolicy,
                             NotesLocalDataSource localDataSource,
                             NotesCloudDataSource cloudDataSource,
                             NoteDataModelMapper mapper) {
    this.cachePolicy = cachePolicy;
    this.localDataSource = localDataSource;
    this.cloudDataSource = cloudDataSource;
    this.mapper = mapper;
  }

  @Override
  public List<Note> getNotes() {
    if (cachePolicy.hasExpired()) {
      cloudDataSource.getNotes();
      cachePolicy.refresh();
    }
    List<Note> result = getNotesFromLocal();
    return result;
  }

  private List<Note> getNotesFromLocal() {
    List<NoteDataModel> actors = localDataSource.getNotes();
    List<Note> result = mapper.map(actors);
    return result;
  }

  @Override
  public Note getNoteById(long id) {
    NoteDataModel actor = localDataSource.getNotesById(id);
    Note result = mapper.map(actor);
    return result;
  }
}
