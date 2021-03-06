package com.example.notesapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;


public class FullNoteOutputFragment extends Fragment {
    View view;
    EditText editTextName;
    EditText editTextDescription;
    private int index;
    private int newItemFlag;
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
        CardsSource currentCard = new CardsSourceImpl(getResources()).init();
        editTextName = view.findViewById(R.id.edit_text_name);
        editTextDescription = view.findViewById(R.id.edit_text_description);
        if (index != -1) {
            Note currentNote = currentCard.getNote(index);
            editTextName.setText(currentNote.getName());
            editTextDescription.setText(currentNote.getDescription());
        } else {
            editTextName.setText("");
            editTextDescription.setText("");
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