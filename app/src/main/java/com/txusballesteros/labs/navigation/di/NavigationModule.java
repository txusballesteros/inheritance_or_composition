package com.txusballesteros.labs.navigation.di;

import com.txusballesteros.labs.navigation.ApplicationNavigator;
import com.txusballesteros.labs.navigation.Navigator;
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
