package com.txusballesteros.labs.view.behavior;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.txusballesteros.labs.R;

abstract class ToolbarBehavior extends Behavior {
  private final AppCompatActivity activity;
  @BindView(R.id.toolbar) Toolbar toolbar;

  ToolbarBehavior(@NonNull Activity activity) {
    if (!(activity instanceof AppCompatActivity)) {
      throw new IllegalArgumentException();
    }
    this.activity = (AppCompatActivity) activity;
  }

  @Override
  protected int onRequestPlaceHolderId() {
    return R.id.toolbar_place_holder;
  }

  @Override
  protected void onBehaviorReady(View rootView, View view) {
    ButterKnife.bind(this, rootView);
    activity.setSupportActionBar(toolbar);
  }
}
