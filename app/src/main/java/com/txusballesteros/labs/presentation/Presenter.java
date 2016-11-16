package com.txusballesteros.labs.presentation;

import android.support.annotation.NonNull;

public interface Presenter<VIEW extends Presenter.View> {
  VIEW getView();
  void onAttach(@NonNull VIEW view);
  void onDetach();
  boolean isViewAttached();

  interface View { }
}
