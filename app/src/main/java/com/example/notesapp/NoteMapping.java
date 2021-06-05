package com.example.notesapp;

import com.google.firebase.Timestamp;

import java.util.HashMap;
import java.util.Map;

public class NoteMapping {

    public static class Fields{
        public final static String NAME = "name";
        public final static String DATE = "date";
        public final static String DESCRIPTION = "description";
    }

    public static Note toNote(String id, Map<String,Object> doc){
        String timestamp = (String) doc.get(Fields.DATE);
        Note answer = new Note((String) doc.get(Fields.NAME),timestamp,(String) doc.get(Fields.DESCRIPTION));
        answer.setId(id);
        return answer;
    }

    public static Map<String, Object> toDocument(Note note){
        Map<String, Object> answer = new HashMap<>();
        answer.put(Fields.DESCRIPTION, note.getDescription());
        answer.put(Fields.DATE, note.getDate());
        answer.put(Fields.NAME,note.getName());
        return answer;
    }


}
