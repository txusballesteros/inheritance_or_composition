package com.txusballesteros.data.model;

import com.txusballesteros.domain.model.TextNote;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TextNoteDataModelMapper {
  @Inject
  public TextNoteDataModelMapper() { }

  public TextNote map(TextNoteDataModel source) {
    final TextNote.Builder builder = new TextNote.Builder();
    builder.setId(source.getId());
    builder.setTitle(source.getTitle());
    builder.setDescription(source.getDescription());
    return (TextNote) builder.build();
  }
}
