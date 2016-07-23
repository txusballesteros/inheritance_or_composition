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
package com.txusballesteros.labs.domain.interactor;

import com.txusballesteros.labs.domain.executor.PostExecutionThread;
import com.txusballesteros.labs.domain.executor.ThreadExecutor;
import com.txusballesteros.labs.domain.repository.NotesRepository;
import javax.inject.Inject;

public class DeleteNoteInteractor implements DeleteNoteUseCase, Runnable {
  private final NotesRepository repository;
  private final ThreadExecutor executor;
  private final PostExecutionThread postExecutor;
  private long noteId;
  private Callback callback;

  @Inject
  public DeleteNoteInteractor(NotesRepository repository,
                              ThreadExecutor executor,
                              PostExecutionThread postExecutor) {
    this.repository = repository;
    this.executor = executor;
    this.postExecutor = postExecutor;
  }

  @Override
  public void execute(long noteId, Callback callback) {
    this.noteId = noteId;
    this.callback = callback;
    executor.execute(this);
  }

  @Override
  public void run() {
    repository.deleteNote(noteId);
    postExecutor.execute(new Runnable() {
      @Override
      public void run() {
        callback.onNoteDeleted();
      }
    });
  }
}
