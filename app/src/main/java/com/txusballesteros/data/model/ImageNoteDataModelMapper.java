package com.txusballesteros.data.model;

import com.txusballesteros.domain.model.ImageNote;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ImageNoteDataModelMapper {
  @Inject
  public ImageNoteDataModelMapper() { }

  public ImageNote map(ImageNoteDataModel source) {
    final ImageNote.Builder builder = new ImageNote.Builder();
    builder.setId(source.getId());
    builder.setTitle(source.getTitle());
    builder.setDescription(source.getDescription());
    builder.setImageUrl(source.getImageUrl());
    return (ImageNote) builder.build();
  }
}
