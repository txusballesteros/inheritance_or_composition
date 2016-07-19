package com.txusballesteros.instrumentation.di;

import com.txusballesteros.instrumentation.ImageDownloader;

public interface InstrumentationProvider {
  ImageDownloader getImageDownloader();
}
