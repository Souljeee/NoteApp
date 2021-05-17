package com.example.notesapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;


public class FullNoteOutputFragment extends Fragment {
    private int index;
    public static final String ARG_INDEX = "index";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_full_note_output, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayout lin = (LinearLayout) view;
        TextView textView = new TextView(getContext());
        String[] arr = getResources().getStringArray(R.array.descriptionOfNote);
        textView.setText(arr[index]);
        textView.setTextColor(getResources().getColor(R.color.black));
        textView.setTextSize(20);
        lin.addView(textView);

    }
}