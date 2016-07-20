package com.txusballesteros.data.notes.strategy;

import com.txusballesteros.data.model.NoteDataModel;
import com.txusballesteros.data.notes.datasource.NotesLocalDataSource;
import com.txusballesteros.data.strategy.LocalStrategy;
import javax.inject.Inject;

public class GetNoteByIdStrategy extends LocalStrategy<NoteDataModel> {
  private final NotesLocalDataSource localDataSource;
  private long id;

  public  NoteDataModel execute(long id) {
    this.id = id;
    return super.execute();
  }

  @Inject
  public GetNoteByIdStrategy(NotesLocalDataSource localDataSource) {
    this.localDataSource = localDataSource;
  }

  @Override
  protected NoteDataModel callToLocalRepository() {
    return localDataSource.getNotesById(id);
  }
}
