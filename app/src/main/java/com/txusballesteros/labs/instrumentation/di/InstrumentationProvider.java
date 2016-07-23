package com.txusballesteros.labs.instrumentation.di;

import com.txusballesteros.labs.instrumentation.ImageDownloader;

public interface InstrumentationProvider {
  ImageDownloader getImageDownloader();
}
