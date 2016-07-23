package com.txusballesteros.labs.view.fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import butterknife.BindView;
import com.txusballesteros.labs.R;
import com.txusballesteros.labs.domain.model.Task;
import com.txusballesteros.labs.domain.model.TaskListNote;

public class TasksListNoteDetailFragment extends AbsFragment {
  @BindView(R.id.tasks_holder) ViewGroup tasksHolderView;
  private TaskListNote note;

  public static TasksListNoteDetailFragment newInstance() {
    return new TasksListNoteDetailFragment();
  }

  public void setNote(@NonNull TaskListNote note) {
    this.note = note;
  }

  @Override
  protected boolean fragmentHasOptionsMenu() {
    return false;
  }

  @Override
  int onRequestLayoutResourceId() {
    return R.layout.fragment_tasks_list_note_detail;
  }

  @Override
  public void onViewReady() {
    renderText();
    renderTask();
  }

  private void renderText() {
    TextNoteDetailFragment fragment = TextNoteDetailFragment.newInstance();
    fragment.setNote(note);
    getActivity().getSupportFragmentManager()
        .beginTransaction()
        .add(R.id.text_note_place_holder, fragment)
        .commit();
  }

  private void renderTask() {
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
