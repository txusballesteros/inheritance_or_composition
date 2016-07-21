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

import com.txusballesteros.data.model.NoteDataModel;
import com.txusballesteros.data.model.NoteDataModelMapper;
import com.txusballesteros.data.notes.strategy.DeleteNoteStrategy;
import com.txusballesteros.data.notes.strategy.GetNoteByIdStrategy;
import com.txusballesteros.data.notes.strategy.GetNotesStrategy;
import com.txusballesteros.data.notes.strategy.StoreNoteStrategy;
import com.txusballesteros.domain.model.Note;
import com.txusballesteros.domain.repository.NotesRepository;
import java.util.List;
import javax.inject.Inject;

public class NotesRepositoryImpl implements NotesRepository {
  private final StoreNoteStrategy storeNoteStrategy;
  private final DeleteNoteStrategy deleteNoteStrategy;
  private final NoteDataModelMapper mapper;
  private final GetNoteByIdStrategy getNoteByIdStrategy;
  private final GetNotesStrategy getNotesStrategy;

  @Inject
  public NotesRepositoryImpl(GetNotesStrategy getNotesStrategy,
                             GetNoteByIdStrategy getNoteByIdStrategy,
                             StoreNoteStrategy storeNoteStrategy,
                             DeleteNoteStrategy deleteNoteStrategy,
                             NoteDataModelMapper mapper) {
    this.getNotesStrategy = getNotesStrategy;
    this.getNoteByIdStrategy = getNoteByIdStrategy;
    this.storeNoteStrategy = storeNoteStrategy;
    this.deleteNoteStrategy = deleteNoteStrategy;
    this.mapper = mapper;
  }

  @Override
  public List<Note> getNotes() {
    List<NoteDataModel> actors = getNotesStrategy.execute();
    List<Note> result = mapper.map(actors);
    return result;
  }

  @Override
  public Note getNoteById(long id) {
    NoteDataModel note = getNoteByIdStrategy.execute(id);
    Note result = mapper.map(note);
    return result;
  }

  @Override
  public void storeNote(Note note) {
    NoteDataModel noteDataModel = mapper.map(note);
    storeNoteStrategy.execute(noteDataModel);
  }

  @Override
  public void deleteNote(long id) {
    deleteNoteStrategy.execute(id);
  }
}
