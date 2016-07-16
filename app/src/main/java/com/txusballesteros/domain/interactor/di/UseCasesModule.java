package com.txusballesteros.domain.interactor.di;

import com.txusballesteros.domain.interactor.GetActorsListInteractor;
import com.txusballesteros.domain.interactor.GetActorsListUseCase;
import dagger.Module;
import dagger.Provides;

@Module
public class UseCasesModule {
  @Provides
  GetActorsListUseCase provideGetActorsListUseCase(GetActorsListInteractor useCase) {
    return useCase;
  }
}
