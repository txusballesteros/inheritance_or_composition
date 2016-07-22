package com.txusballesteros.view.fragment;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import butterknife.BindView;
import com.txusballesteros.R;
import com.txusballesteros.domain.model.ImageNote;
import com.txusballesteros.domain.model.Note;

public class ImageNoteDetailFragment extends TextNoteDetailFragment {
  @BindView(R.id.headerImage) ImageView imageView;
  @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;

  public static Fragment newInstance(long noteId) {
    Bundle arguments = new Bundle(1);
    arguments.putLong(EXTRA_NOTE_ID, noteId);
    Fragment result = new ImageNoteDetailFragment();
    result.setArguments(arguments);
    return result;
  }

  @Override
  int onRequestLayoutResourceId() {
    return R.layout.fragment_image_note_detail;
  }

  @Override
  public void renderNote(Note note) {
    super.renderNote(note);
    renderImage((ImageNote) note);
    renderToolbar(note);
  }

  private void renderImage(ImageNote note) {
    imageDownloader.downloadImage(note.getImageUrl(), imageView);
  }

  private void renderToolbar(Note note) {
    collapsingToolbarLayout.setTitle(note.getTitle());
  }
}
