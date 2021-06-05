package com.example.notesapp;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

public class CardsSourceImpl implements CardsSource {
    private List<Note> dataSource;
    private Resources resources;


    public CardsSourceImpl(Resources resources) {
        dataSource = new ArrayList<>();
        this.resources = resources;
    }

    public CardsSourceImpl init() {
        String[] namesOfNote = resources.getStringArray(R.array.nameOfNotes);
        String[] descriptionsOfNote = resources.getStringArray(R.array.descriptionOfNote);
        String[] datesOfNote = resources.getStringArray(R.array.dateOfCreate);
        for (int i = 0; i < namesOfNote.length; i++) {
            dataSource.add(new Note(namesOfNote[i], datesOfNote[i], descriptionsOfNote[i]));
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
}
