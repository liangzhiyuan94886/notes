package com.learn.notes.service;

import com.learn.notes.dao.NotesDao;
import com.learn.notes.entity.Notes;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "notesDaoImpl")
public class NotesDaoImpl implements NotesDao {
    @Resource
    private NotesDao notesDao;

    /**
     * 查询全部笔记
     * @return
     */
    @Override
    public List<Notes> getALLNotes() {
        return notesDao.getALLNotes();
    }

    @Override
    public String updateDescribe(Notes notes) {
        return notesDao.updateDescribe(notes);
    }

    @Override
    public String addNote(Notes notes) {
        return notesDao.addNote(notes);
    }

}
