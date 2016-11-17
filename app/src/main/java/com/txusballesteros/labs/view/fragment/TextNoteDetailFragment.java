package com.txusballesteros.labs.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;
import butterknife.BindView;
import com.txusballesteros.labs.R;
import com.txusballesteros.labs.domain.model.Note;

public class TextNoteDetailFragment extends AbsFragment {
  public static final String EXTRA_NOTE_TITLE = "note:title";
  public static final String EXTRA_NOTE_DESCRIPTION = "note:description";
  private String noteTitle;
  private String noteDescription;
  @BindView(R.id.title) TextView titleView;
  @BindView(R.id.description) TextView descriptionView;

  public static TextNoteDetailFragment newInstance() {
    return new TextNoteDetailFragment();
  }

  public void setNote(@NonNull Note note) {
    this.noteTitle = note.getTitle();
    this.noteDescription = note.getDescription();
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(false);
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    outState.putString(EXTRA_NOTE_TITLE, noteTitle);
    outState.putString(EXTRA_NOTE_DESCRIPTION, noteDescription);
    super.onSaveInstanceState(outState);
  }

  @Override
  public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
    if (savedInstanceState != null) {
      noteTitle = savedInstanceState.getString(EXTRA_NOTE_TITLE);
      noteDescription = savedInstanceState.getString(EXTRA_NOTE_DESCRIPTION);
    }
    super.onViewStateRestored(savedInstanceState);
  }

  @Override
  protected int onRequestLayoutResourceId() {
    return R.layout.fragment_text_note_detail;
  }

  @Override
  public void onViewReady() {
    renderTitle();
    renderDescription();
  }

  private void renderTitle() {
    titleView.setText(noteTitle);
  }

  private void renderDescription() {
    descriptionView.setText(noteDescription);
  }
}
