package com.learn.notes.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.learn.notes.entity.Notes;
import com.learn.notes.service.NotesDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class NotesController {
    @Autowired
    private NotesDaoImpl notesDaoImpl;

    @RequestMapping("/quicknotes")
    public String hello() {
        return "quicknotes";
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
        System.out.println(getNotes);
        return result;
    }

    @RequestMapping("/updateDescribe")
    @ResponseBody
    public Notes updateDescribe(String describe, String id) {
        Notes notes = new Notes();
        try {
            notes.setDescribe(describe);
            notes.setId(Integer.parseInt(id));
            notesDaoImpl.updateDescribe(notes);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return notes;
    }

    @RequestMapping("/addNote")
    @ResponseBody
    public Notes addNote(String describe,Integer type) {
        Notes notes = new Notes();
        try {
            notes.setDescribe(describe);
            notes.setId(type);
            notesDaoImpl.addNote(notes);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return notes;
    }
}
