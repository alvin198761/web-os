package org.alvin.opsdev.desktop.system.controller;

import org.alvin.opsdev.desktop.system.bean.NotesBean;
import org.alvin.opsdev.desktop.system.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by tangzhichao on 2017/3/6.
 */
@RestController
public class NotesController {

    @Autowired
    private NotesService notesService;

    @RequestMapping(value = "/api/note", method = RequestMethod.GET)
    public List<NotesBean> notes() {
        return notesService.list();
    }


}
