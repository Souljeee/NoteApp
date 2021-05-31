package com.example.notesapp;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

public class CardsSource {
    private static List<Note> dataSource = new ArrayList<>();
    private Resources resources;


    public static Note getNote(int position) {
        return dataSource.get(position);
    }

    public static int size() {
        return dataSource.size();
    }


    public static void deleteNote(int position) {
        dataSource.remove(position);
    }


    public static void updateNote(int position, Note note) {
        dataSource.set(position, note);
    }


    public static void addNote(Note note) {
        dataSource.add(note);
    }


    public static void clearNote() {
        dataSource.clear();
    }
}
