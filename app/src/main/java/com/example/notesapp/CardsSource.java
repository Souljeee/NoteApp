package com.example.notesapp;

public interface CardsSource {
    CardsSource init(CardsSourceResponse cardsSourceResponse);
    Note getNote(int position);
    int size();
    void deleteNote(int position);
    void updateNote(int position, Note note);
    void addNote(Note note);
    void clearNote();
}
