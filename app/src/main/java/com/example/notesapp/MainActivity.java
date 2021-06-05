package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class MainActivity extends AppCompatActivity {

    private final int NEW_ITEM_FLAG = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().getInt(OpenNoteActivity.NEW_NOTE) == 1) {
                if (savedInstanceState == null) {
                    NoteOutputFragment input = new NoteOutputFragment();
                    input.setArguments(getIntent().getExtras());
                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(R.id.fragment_output_container, input, null)
                            .commit();
                }
            }
        }else{
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_output_container, new NoteOutputFragment(), null)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_add:
                Intent intent = new Intent(this, OpenNoteActivity.class);
                intent.putExtra(FullNoteOutputFragment.ARG_INDEX, NEW_ITEM_FLAG);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}