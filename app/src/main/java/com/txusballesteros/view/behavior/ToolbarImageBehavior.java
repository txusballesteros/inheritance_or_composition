package com.txusballesteros.view.behavior;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.BindView;
import com.txusballesteros.R;

public class ToolbarImageBehavior extends ToolbarBehavior {
  @BindView(R.id.toolbar) Toolbar toolbar;

  public ToolbarImageBehavior(@NonNull Activity activity) {
    super(activity);
  }

  @Override
  protected int onRequestLayoutResourceId() {
    return R.layout.toolbar_with_image;
  }

  @Override
  protected void onBehaviorReady(View rootView, View view) {
    super.onBehaviorReady(rootView, view);
    AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) view.getLayoutParams();
    params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                          | AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
    view.setLayoutParams(params);
  }
}
