package com.txusballesteros.domain.interactor;

import com.txusballesteros.domain.model.Note;

public interface StoreNoteUseCase {
  void execute(Note note, Callback callback);

  interface Callback {
    void onNoteSaved();
  }
}
