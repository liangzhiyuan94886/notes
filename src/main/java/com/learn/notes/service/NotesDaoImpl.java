package com.learn.notes.service;

import com.learn.notes.dao.NotesDao;
import com.learn.notes.entity.Notes;
import com.learn.notes.entity.Tag;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "notesDaoImpl")
public class NotesDaoImpl implements NotesDao {
    @Resource
    private NotesDao notesDao;

    /**
     * 查询标签
     * @return
     */
    @Override
    public List<Tag> getAllTags() {
        return notesDao.getAllTags();
    }

    /**
     * 查询全部笔记
     * @return
     */
    @Override
    public List<Notes> getALLNotes() {
        return notesDao.getALLNotes();
    }

    /**
     * update内容详情
     * @param notes
     * @return
     */
    @Override
    public String updateContent(Notes notes) {
        return notesDao.updateContent(notes);
    }

    /**
     * 新增信息
     * @param notes
     * @return
     */
    @Override
    public String addNote(Notes notes) {
        return notesDao.addNote(notes);
    }

    /**
     * 新增tag
     * @param tag
     * @return
     */
    @Override
    public String addTag(Tag tag) {
        return notesDao.addTag(tag);
    }

}
