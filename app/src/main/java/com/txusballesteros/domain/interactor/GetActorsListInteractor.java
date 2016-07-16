package com.txusballesteros.domain.interactor;

import android.support.annotation.NonNull;
import com.txusballesteros.domain.executor.PostExecutionThread;
import com.txusballesteros.domain.executor.ThreadExecutor;
import com.txusballesteros.domain.model.Actor;
import com.txusballesteros.domain.repository.ActorsRepository;
import java.util.List;
import javax.inject.Inject;

public class GetActorsListInteractor implements GetActorsListUseCase, Runnable {
  private final ActorsRepository repository;
  private final ThreadExecutor executor;
  private final PostExecutionThread postExecutor;
  private Callback callback;

  @Inject
  public GetActorsListInteractor(ActorsRepository repository,
                                 ThreadExecutor executor, PostExecutionThread postExecutor) {
    this.repository = repository;
    this.executor = executor;
    this.postExecutor = postExecutor;
  }

  @Override
  public void execute(@NonNull Callback callback) {
    this.callback = callback;
    executor.execute(this);
  }

  @Override
  public void run() {
    final List<Actor> actors = repository.getActors();
    postExecutor.execute(new Runnable() {
      @Override
      public void run() {
        callback.onActorReady(actors);
      }
    });
  }
}
