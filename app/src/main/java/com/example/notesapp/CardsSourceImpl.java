package com.example.notesapp;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

public class CardsSourceImpl implements CardsSource {
    private List<Note> dataSource = new ArrayList<>();
    private Resources resources;    // ресурсы приложения

    public CardsSourceImpl(Resources resources) {
        dataSource = new ArrayList<>(7);
        this.resources = resources;
    }


    @Override
    public CardsSource init(CardsSourceResponse cardsSourceResponse) {
        if (cardsSourceResponse != null){
            cardsSourceResponse.initialized(this);
        }
        return this;
    }

    @Override
    public Note getNote(int position) {
        return dataSource.get(position);
    }

    @Override
    public int size() {
        return dataSource.size();
    }

    @Override
    public void deleteNote(int position) {
        dataSource.remove(position);
    }

    @Override
    public void updateNote(int position, Note note) {
        dataSource.set(position, note);
    }

    @Override
    public void addNote(Note note) {
        dataSource.add(note);
    }

    @Override
    public void clearNote() {
        dataSource.clear();
    }
}
