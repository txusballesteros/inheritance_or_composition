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
package com.txusballesteros.labs.view.adapter.renderer.notes;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.txusballesteros.labs.R;
import com.txusballesteros.labs.domain.model.NoteType;
import com.txusballesteros.labs.instrumentation.ImageDownloader;

public class NoteAdapterViewHolderFactory {

  public static NoteAdapterViewHolder build(@NonNull ViewGroup parent,
                                            @NonNull NoteType type,
                                            @NonNull ImageDownloader imageDownloader,
                                            @NonNull NoteAdapterViewHolder.OnViewHolderClickListener listener) {
    NoteAdapterViewHolder result;
    switch(type) {
      case IMAGE:
        result = buildImageAdapterViewHolder(parent, imageDownloader, listener);
        break;
      case TASK_LIST:
        result = buildTasksListAdapterViewHolder(parent, listener);
        break;
      default:
        result = buildTextAdapterViewHolder(parent, listener);
        break;
    }
    return result;
  }

  private static NoteAdapterViewHolder buildImageAdapterViewHolder(ViewGroup parent,
                                                                   ImageDownloader imageDownloader,
                                                                   NoteAdapterViewHolder.OnViewHolderClickListener listener) {
    View holderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note_image, parent, false);
    return new ImageNoteAdapterViewHolder(holderView, listener, imageDownloader);
  }

  private static NoteAdapterViewHolder buildTextAdapterViewHolder(ViewGroup parent,
                                                                  NoteAdapterViewHolder.OnViewHolderClickListener listener) {
    View holderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note_text, parent, false);
    return new TextNoteAdapterViewHolder(holderView, listener);
  }

  private static NoteAdapterViewHolder buildTasksListAdapterViewHolder(ViewGroup parent,
                                                                       NoteAdapterViewHolder.OnViewHolderClickListener listener) {
    View holderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note_tasks_list, parent, false);
    return new TasksListNoteAdapterViewHolder(holderView, listener);
  }
}
