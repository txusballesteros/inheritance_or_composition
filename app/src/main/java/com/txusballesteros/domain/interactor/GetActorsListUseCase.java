package com.txusballesteros.domain.interactor;

import android.support.annotation.NonNull;
import com.txusballesteros.domain.model.Actor;
import java.util.List;

public interface GetActorsListUseCase {
  void execute(@NonNull Callback callback);

  interface Callback {
    void onActorReady(@NonNull List<Actor> actors);
  }
}
