package com.txusballesteros.view.fragment;

import android.support.v4.app.Fragment;
import com.txusballesteros.R;

public class AboutFragment extends AbsFragment {
  public static Fragment newInstance() {
    return new AboutFragment();
  }

  @Override
  int onRequestLayoutResourceId() {
    return R.layout.fragment_about;
  }
}
