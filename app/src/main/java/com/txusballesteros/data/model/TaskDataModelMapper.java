package com.txusballesteros.data.model;

import com.txusballesteros.domain.model.Task;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TaskDataModelMapper {
  @Inject
  public TaskDataModelMapper() { }

  public List<Task> map(List<TaskDataModel> source) {
    List<Task> result = new ArrayList<>(source.size());
    for(TaskDataModel task : source) {
      result.add(map(task));
    }
    return result;
  }

  public Task map(TaskDataModel source) {
    return new Task.Builder()
           .setOrder(source.getOrder())
           .setTitle(source.getTitle())
           .setIsDone(source.isDone())
           .build();
  }
}
