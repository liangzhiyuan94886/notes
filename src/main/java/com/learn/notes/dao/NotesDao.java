package com.learn.notes.dao;

import com.learn.notes.entity.Notes;
import com.learn.notes.entity.Tag;

import java.util.List;

public interface NotesDao {
    List<Tag> getAllTags();

    List<Notes> getALLNotes();

    String updateContent(Notes notes);

    String addNote(Notes notes);

    String addTag(Tag tag);

}
