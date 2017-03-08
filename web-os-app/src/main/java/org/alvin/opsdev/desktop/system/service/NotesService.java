package org.alvin.opsdev.desktop.system.service;

import com.google.common.collect.Lists;
import org.alvin.opsdev.desktop.system.bean.NotesBean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tangzhichao on 2017/3/6.
 */
@Service
public class NotesService {

    public List<NotesBean> list(){
        List<NotesBean> notes = Lists.newArrayList();
        NotesBean notesBean = new NotesBean();
        notesBean.setId("001");
        notesBean.setTitle("便签");
        notesBean.setContent("");
        notes.add(notesBean);
        return notes;
    }
}
