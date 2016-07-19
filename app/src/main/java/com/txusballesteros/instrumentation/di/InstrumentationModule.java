package com.txusballesteros.instrumentation.di;

import com.txusballesteros.instrumentation.ImageDownloader;
import com.txusballesteros.instrumentation.PicassoImageDownloader;
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
