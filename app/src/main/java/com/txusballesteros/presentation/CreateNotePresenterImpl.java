package com.txusballesteros.presentation;

import android.support.annotation.NonNull;
import com.txusballesteros.domain.interactor.StoreNoteUseCase;
import com.txusballesteros.domain.model.Note;
import javax.inject.Inject;

public class CreateNotePresenterImpl implements CreateNotePresenter {
  private final View view;
  private final StoreNoteUseCase storeNoteUseCase;

  @Inject
  public CreateNotePresenterImpl(View view, StoreNoteUseCase storeNoteUseCase) {
    this.view = view;
    this.storeNoteUseCase = storeNoteUseCase;
  }

  @Override
  public void onCreateNote(@NonNull Note note) {
    storeNoteUseCase.execute(note, new StoreNoteUseCase.Callback() {
      @Override
      public void onNoteSaved() {
        view.closeView();
      }
    });
  }
}
