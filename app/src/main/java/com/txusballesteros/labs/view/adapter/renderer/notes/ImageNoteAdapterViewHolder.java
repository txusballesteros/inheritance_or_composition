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
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import com.txusballesteros.labs.R;
import com.txusballesteros.labs.domain.model.ImageNote;
import com.txusballesteros.labs.domain.model.Note;
import com.txusballesteros.labs.instrumentation.ImageDownloader;

public class ImageNoteAdapterViewHolder extends TextNoteAdapterViewHolder {
  @NonNull private final ImageDownloader imageDownloader;
  @BindView(R.id.image) ImageView imageView;

  public ImageNoteAdapterViewHolder(@NonNull View view,
                                    @NonNull OnViewHolderClickListener listener,
                                    @NonNull ImageDownloader imageDownloader) {
    super(view, listener);
    this.imageDownloader = imageDownloader;
  }

  @Override
  public void render(@NonNull Note note) {
    super.render(note);
    renderImage(note);
  }

  private void renderImage(@NonNull Note note) {
    if (note instanceof ImageNote) {
      final ImageNote imageNote = (ImageNote) note;
      imageDownloader.downloadImage(imageNote.getImageUrl(), imageView);
    }
  }

  @Nullable @Override
  protected View getSharedView() {
    return imageView;
  }
}
