package com.learn.notes.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.learn.notes.entity.Notes;
import com.learn.notes.entity.Tag;
import com.learn.notes.service.NotesDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class NotesController {
    @Autowired
    private NotesDaoImpl notesDaoImpl;

    @RequestMapping("/quicknotes")
    public String starMyNotes() {
        return "quicknotes";
    }

    /**
     * 查询tag
     * @return
     */
    @RequestMapping("/getAllTags")
    @ResponseBody
    public List<Tag> getAllTags(){
        List<Tag> getAllTag = notesDaoImpl.getAllTags();
        return getAllTag;
    }

    /**
     * 查询全部notes
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/getALLNotes")
    @ResponseBody
    public Map<String, Object> getALLNotes(Integer page,Integer limit){
        Map<String, Object> result = new HashMap<>();
        PageHelper.startPage(page,limit);
        List<Notes> getNotes = notesDaoImpl.getALLNotes();
        PageInfo<Notes> pageInfo = new PageInfo(getNotes);
        result.put("total", pageInfo.getTotal());
        result.put("data", pageInfo);
        return result;
    }

    /**
     * update关键词和描述内容
     * @param value
     * @param id
     * @param field
     * @return
     */
    @RequestMapping("/updateNote")
    @ResponseBody
    public Notes updateNote(String value, String id, String field) {
        Notes notes = new Notes();
        notes.setId(Integer.parseInt(id));
        if (field.equals("keyword")) {
            notes.setKeyword(value);
            notesDaoImpl.updateKeyword(notes);
        }else {
            notes.setContent(value);
            notesDaoImpl.updateContent(notes);
        }
        return notes;
    }

    /**
     * 新增笔记
     * @param content
     * @param keyword
     * @param nid
     * @return
     */
    @RequestMapping("/addNote")
    @ResponseBody
    public Notes addNote(String content,String keyword,String nid) {
        Notes notes = new Notes();
        try {
            notes.setContent(content);
            notes.setKeyword(keyword);
            notes.setNid(nid);
            notesDaoImpl.addNote(notes);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return notes;
    }

    /**
     * 新增标签
     * @param tagName
     * @return
     */
    @RequestMapping("/addTag")
    @ResponseBody
    public Tag addTag(String tagName) {
        String tid = UUID.randomUUID().toString();
        Tag tag = new Tag();
        try {
            tag.setTagName(tagName);
            tag.setTid(tid);
            notesDaoImpl.addTag(tag);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return tag;
    }

    /**
     * 模糊查询匹配
     * @param search
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/searchNotes")
    @ResponseBody
    public Map<String, Object> searchNotes(String search, Integer page, Integer limit) {
        Map<String, Object> result = new HashMap<>();
        PageHelper.startPage(page,limit);
        List<Notes> searchInfo = null;
        if (search.trim().length() == 0) {
            searchInfo = notesDaoImpl.getALLNotes();
        }else {
            searchInfo = notesDaoImpl.searchNotes(search);
        }
        PageInfo<Notes> pageInfo = new PageInfo(searchInfo);
        result.put("total", pageInfo.getTotal());
        result.put("data", pageInfo);
        return result;
    }
}
