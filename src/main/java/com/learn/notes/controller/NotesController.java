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

    @RequestMapping("/getAllTags")
    @ResponseBody
    public List<Tag> getAllTags(){
        List<Tag> getAllTag = notesDaoImpl.getAllTags();
        return getAllTag;
    }

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

    @RequestMapping("/updateContent")
    @ResponseBody
    public Notes updateContent(String content, String id) {
        Notes notes = new Notes();
        try {
            notes.setContent(content);
            notes.setId(Integer.parseInt(id));
            notesDaoImpl.updateContent(notes);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return notes;
    }

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
}
