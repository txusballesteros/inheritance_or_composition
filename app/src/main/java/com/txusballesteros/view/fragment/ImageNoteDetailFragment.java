package com.txusballesteros.view.fragment;

import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.widget.ImageView;
import butterknife.BindView;
import com.txusballesteros.R;
import com.txusballesteros.domain.model.ImageNote;
import com.txusballesteros.instrumentation.ImageDownloader;

public class ImageNoteDetailFragment extends AbsFragment {
  private ImageDownloader imageDownloader;
  private ImageNote note;
  @BindView(R.id.headerImage) ImageView imageView;
  @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;

  public static ImageNoteDetailFragment newInstance() {
    return new ImageNoteDetailFragment();
  }

  public void setImageDownloader(@NonNull ImageDownloader imageDownloader) {
    this.imageDownloader = imageDownloader;
  }

  public void setNote(@NonNull ImageNote note) {
    this.note = note;
  }

  @Override
  protected boolean fragmentHasOptionsMenu() {
    return false;
  }

  @Override
  int onRequestLayoutResourceId() {
    return R.layout.fragment_image_note_detail;
  }

  @Override
  public void onViewReady() {
    renderImage();
    renderText();
    renderToolbar();
  }

  private void renderToolbar() {
    collapsingToolbarLayout.setTitle(note.getTitle());
  }

  private void renderText() {

    TextNoteDetailFragment fragment = TextNoteDetailFragment.newInstance();
    fragment.setNote(note);
    getActivity().getSupportFragmentManager()
        .beginTransaction()
        .add(R.id.text_note_place_holder, fragment)
        .commit();
  }

  private void renderImage() {
    imageDownloader.downloadImage(note.getImageUrl(), imageView);
  }
}
