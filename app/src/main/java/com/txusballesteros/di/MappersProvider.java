package com.txusballesteros.di;

import com.txusballesteros.data.model.ImageNoteDataModelMapper;
import com.txusballesteros.data.model.NoteDataModelMapper;
import com.txusballesteros.data.model.TaskDataModelMapper;
import com.txusballesteros.data.model.TasksListNoteDataModelMapper;
import com.txusballesteros.data.model.TextNoteDataModelMapper;

public interface MappersProvider {
  NoteDataModelMapper getNoteDataModelMapper();
  TextNoteDataModelMapper getTextNoteDataModelMapper();
  TasksListNoteDataModelMapper getTasksListNoteDataModelMapper();
  TaskDataModelMapper getTaskDataModelMapper();
  ImageNoteDataModelMapper getImageNoteDataModelMapper();
}
