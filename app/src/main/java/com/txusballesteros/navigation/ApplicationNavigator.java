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
package com.txusballesteros.navigation;

import android.content.Context;
import android.support.annotation.NonNull;
import com.txusballesteros.domain.model.Note;
import com.txusballesteros.domain.model.NoteType;
import com.txusballesteros.navigation.command.CreateNewNoteNavigationCommand;
import com.txusballesteros.navigation.command.NavigationCommand;
import com.txusballesteros.navigation.command.NoteDetailNavigationCommand;
import javax.inject.Inject;

public class ApplicationNavigator implements Navigator {
  @Inject
  public ApplicationNavigator() { }

  @Override
  public void navigateToCreateNewNote(@NonNull Context context, @NonNull NoteType type) {
    final NavigationCommand navigationCommand = new CreateNewNoteNavigationCommand(context, type);
    navigate(navigationCommand);
  }

  @Override
  public void navigateToNoteDetail(@NonNull Context context, @NonNull Note note) {
    final NavigationCommand navigationCommand = new NoteDetailNavigationCommand(context, note);
    navigate(navigationCommand);
  }

  private void navigate(NavigationCommand navigationCommand) {
    navigationCommand.execute();
  }
}
