package com.txusballesteros.labs.di;

import com.txusballesteros.labs.data.model.ImageNoteDataModelMapper;
import com.txusballesteros.labs.data.model.NoteDataModelMapper;
import com.txusballesteros.labs.data.model.TaskDataModelMapper;
import com.txusballesteros.labs.data.model.TasksListNoteDataModelMapper;
import com.txusballesteros.labs.data.model.TextNoteDataModelMapper;

public interface MappersProvider {
  NoteDataModelMapper getNoteDataModelMapper();
  TextNoteDataModelMapper getTextNoteDataModelMapper();
  TasksListNoteDataModelMapper getTasksListNoteDataModelMapper();
  TaskDataModelMapper getTaskDataModelMapper();
  ImageNoteDataModelMapper getImageNoteDataModelMapper();
}
