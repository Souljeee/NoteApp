package com.example.notesapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;


public class FullNoteOutputFragment extends Fragment {
    Date currentTime;
    View view;
    EditText editTextName;
    EditText editTextDescription;
    private int index;
    public static final String ARG_INDEX = "index";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_INDEX);
        }
    }

    public static FullNoteOutputFragment newInstance(int index) {
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        FullNoteOutputFragment f = new FullNoteOutputFragment();
        f.setArguments(args);
        return f;
    }

    public void setData(View view) {
        editTextName = view.findViewById(R.id.edit_text_name);
        editTextDescription = view.findViewById(R.id.edit_text_description);
        if (index != -1) {
            Note currentNote = CardsSource.getNote(index);
            editTextName.setText(currentNote.getName());
            editTextDescription.setText(currentNote.getDescription());
        } else {
            editTextName.setText("");
            editTextDescription.setText("");
        }
    }

    public void saveData() {
        editTextName = view.findViewById(R.id.edit_text_name);
        editTextDescription = view.findViewById(R.id.edit_text_description);
        currentTime = Calendar.getInstance().getTime();
        Note note = new Note(editTextName.getText().toString(), currentTime.toString(), editTextDescription.getText().toString());
        if (index != -1) {
            CardsSource.updateNote(index, note);
        } else {
            CardsSource.addNote(note);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_full_note_output, container, false);
        setData(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}