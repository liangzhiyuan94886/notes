package com.learn.notes.dao;

import com.learn.notes.entity.Notes;

import java.util.List;

public interface NotesDao {

    List<Notes> getALLNotes();

    String updateDescribe(Notes notes);

    String addNote(Notes notes);

}
