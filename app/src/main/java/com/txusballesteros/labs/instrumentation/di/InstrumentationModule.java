package com.txusballesteros.labs.instrumentation.di;

import com.txusballesteros.labs.instrumentation.ImageDownloader;
import com.txusballesteros.labs.instrumentation.PicassoImageDownloader;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class InstrumentationModule {
  @Provides @Singleton
  ImageDownloader provideImageDownloader(PicassoImageDownloader imageDownloader) {
    return imageDownloader;
  }
}
