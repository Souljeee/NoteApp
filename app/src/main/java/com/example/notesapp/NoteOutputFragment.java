package com.example.notesapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class NoteOutputFragment extends Fragment {
    public static final String CURRENT_NOTE = "CurrentNote";
    private int currentPosition = 0;
    private boolean isLandscape;

    private final int NOTES_COUNT = 5;
    private final int PADDING_BOTTOM = 30;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_output, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initNotes(view);
    }

    private void initNotes(View view) {
        LinearLayout layout = (LinearLayout) view;
        String[] nameOfNotes = getResources().getStringArray(R.array.nameOfNotes);
        String[] dateOfCreate = getResources().getStringArray(R.array.dateOfCreate);

        Note[] notes = new Note[5];
        for (int i = 0; i < NOTES_COUNT; i++) {
            notes[i] = new Note(nameOfNotes[i],dateOfCreate[i],null);
        }

        for (int i = 0; i < NOTES_COUNT; i++) {
            String text = notes[i].getName() + System.lineSeparator() + notes[i].getDate();
            TextView note = new TextView(getContext());
            note.setTextColor(getResources().getColor(R.color.black));
            note.setTextSize(20);
            note.setText(text);
            note.setPadding(0, 0, 0, PADDING_BOTTOM);
            layout.addView(note);

            final int fi = i;
            note.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentPosition = fi;
                    show(currentPosition);
                }
            });
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(CURRENT_NOTE, currentPosition);
        super.onSaveInstanceState(outState);
    }

    public void showPort(int fi){
        Intent intent = new Intent(getActivity(),OpenNoteActivity.class);
        intent.putExtra(FullNoteOutputFragment.ARG_INDEX,fi);
        startActivity(intent);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(CURRENT_NOTE, 0);
        }
        if(isLandscape){
            showLand(currentPosition);
        }
    }

    private void show(int index){
        if(isLandscape){
            showLand(index);
        }else{
            showPort(index);
        }
    }

    private void showLand(int index){
        FullNoteOutputFragment detail = FullNoteOutputFragment.newInstance(index);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.full_note_fragment,detail);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }
}
