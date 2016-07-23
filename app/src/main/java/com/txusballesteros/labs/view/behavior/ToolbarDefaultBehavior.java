package com.txusballesteros.labs.view.behavior;

import android.app.Activity;
import android.support.annotation.NonNull;
import com.txusballesteros.labs.R;

public class ToolbarDefaultBehavior extends ToolbarBehavior {
  public ToolbarDefaultBehavior(@NonNull Activity activity) {
    super(activity);
  }

  @Override
  protected int onRequestLayoutResourceId() {
    return R.layout.toolbar_default;
  }
}
