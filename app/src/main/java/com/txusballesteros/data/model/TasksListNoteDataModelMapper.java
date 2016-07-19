package com.txusballesteros.data.model;

import com.txusballesteros.domain.model.Task;
import com.txusballesteros.domain.model.TaskListNote;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TasksListNoteDataModelMapper {
  private final TaskDataModelMapper taskDataModelMapper;

  @Inject
  public TasksListNoteDataModelMapper(TaskDataModelMapper taskDataModelMapper) {
    this.taskDataModelMapper = taskDataModelMapper;
  }

  public TaskListNote map(TaskListNoteDataModel source) {
    final List<Task> tasks = taskDataModelMapper.map(source.getTasks());
    final TaskListNote.Builder builder = new TaskListNote.Builder();
    builder.setId(source.getId());
    builder.setTitle(source.getTitle());
    builder.setDescription(source.getDescription());
    builder.setTasks(tasks);
    return (TaskListNote) builder.build();
  }
}
