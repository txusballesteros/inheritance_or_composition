package com.txusballesteros.navigation.di;

import com.txusballesteros.navigation.ApplicationNavigator;
import com.txusballesteros.navigation.Navigator;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class NavigationModule {
  @Singleton @Provides
  Navigator provideNavigator(ApplicationNavigator navigator) {
    return navigator;
  }
}
