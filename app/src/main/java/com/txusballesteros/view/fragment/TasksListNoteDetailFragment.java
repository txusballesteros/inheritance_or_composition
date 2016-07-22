package com.txusballesteros.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import butterknife.BindView;
import com.txusballesteros.R;
import com.txusballesteros.domain.model.Note;
import com.txusballesteros.domain.model.Task;
import com.txusballesteros.domain.model.TaskListNote;

public class TasksListNoteDetailFragment extends TextNoteDetailFragment {
  @BindView(R.id.tasks_holder) ViewGroup tasksHolderView;

  public static Fragment newInstance(long noteId) {
    Bundle arguments = new Bundle(1);
    arguments.putLong(EXTRA_NOTE_ID, noteId);
    Fragment result = new TasksListNoteDetailFragment();
    result.setArguments(arguments);
    return result;
  }

  @Override
  int onRequestLayoutResourceId() {
    return R.layout.fragment_tasks_list_note_detail;
  }

  @Override
  public void renderNote(Note note) {
    super.renderNote(note);
    renderTask((TaskListNote) note);
  }

  private void renderTask(TaskListNote note) {
    tasksHolderView.removeAllViews();
    for(Task task : note.getTasks()) {
      renderTask(task);
    }
  }

  private void renderTask(Task task) {
    final Context context = getView().getContext();
    CheckBox taskView = new CheckBox(context);
    taskView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                           ViewGroup.LayoutParams.WRAP_CONTENT));
    taskView.setTextAppearance(context, R.style.task);
    taskView.setText(task.getTitle());
    taskView.setChecked(task.isDone());
    tasksHolderView.addView(taskView);
  }
}
