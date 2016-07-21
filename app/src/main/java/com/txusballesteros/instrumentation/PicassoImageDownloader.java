package com.txusballesteros.instrumentation;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import com.txusballesteros.R;
import javax.inject.Inject;

public class PicassoImageDownloader implements ImageDownloader {
  private Context context;

  @Inject
  public PicassoImageDownloader(Application application) {
    this.context = application;
  }

  @Override
  public void downloadImage(@NonNull String imageUrl, ImageView view) {
    Picasso.with(context)
        .load(imageUrl)
        .placeholder(R.drawable.dummy_image)
        .into(view);
  }
}
