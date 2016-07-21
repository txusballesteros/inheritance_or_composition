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
package com.txusballesteros.navigation.command;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import com.txusballesteros.R;
import com.txusballesteros.domain.model.Note;
import com.txusballesteros.domain.model.NoteType;
import com.txusballesteros.view.activity.NoteDetailActivity;

public class NoteDetailNavigationCommand extends NavigationCommand {
  private final Note note;
  private final View sharedView;

  public NoteDetailNavigationCommand(Context context, Note note, View sharedView) {
    super(context);
    this.note = note;
    this.sharedView = sharedView;
  }

  @NonNull @Override
  Intent onRequestIntent(Context context) {
    final Intent intent = new Intent(context, NoteDetailActivity.class);
    intent.putExtra(NoteDetailActivity.EXTRA_NOTE_TYPE, note.getType().ordinal());
    intent.putExtra(NoteDetailActivity.EXTRA_NOTE_ID, note.getId());
    return intent;
  }

  @Nullable @Override
  protected Bundle onRequestActivityOptions() {
    Bundle result = null;
    if (sharedView != null && note.getType() == NoteType.IMAGE) {
      final Activity activity = (Activity) getContext();
      final Resources resources = activity.getResources();
      ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat
              .makeSceneTransitionAnimation(activity, sharedView, resources.getString(R.string.transition_note_image));
      result = activityOptionsCompat.toBundle();
    }
    return result;
  }
}
