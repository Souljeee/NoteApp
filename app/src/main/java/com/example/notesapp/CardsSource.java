package com.example.notesapp;

public interface CardsSource {
    Note getNote(int position);
    int size();
}
