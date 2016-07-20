package com.txusballesteros.domain.interactor;

import android.support.annotation.NonNull;
import com.txusballesteros.domain.executor.PostExecutionThread;
import com.txusballesteros.domain.executor.ThreadExecutor;
import com.txusballesteros.domain.model.Note;
import com.txusballesteros.domain.repository.NotesRepository;
import javax.inject.Inject;

public class StoreNoteUseInterface implements StoreNoteUseCase, Runnable {
  private final NotesRepository repository;
  private final ThreadExecutor executor;
  private final PostExecutionThread postExecutor;
  private Note note;
  private Callback callback;

  @Inject
  public StoreNoteUseInterface(NotesRepository repository,
                               ThreadExecutor executor,
                               PostExecutionThread postExecutor) {

    this.repository = repository;
    this.executor = executor;
    this.postExecutor = postExecutor;
  }

  @Override
  public void execute(@NonNull Note note, @NonNull Callback callback) {
    this.note = note;
    this.callback = callback;
    executor.execute(this);
  }

  @Override
  public void run() {
    repository.storeNote(note);
    postExecutor.execute(new Runnable() {
      @Override
      public void run() {
        callback.onNoteSaved();
      }
    });
  }
}
