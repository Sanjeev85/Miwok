package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Family_Members extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

         ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("father","epe"));
        words.add(new Word("mother","eta"));
        words.add(new Word("son","angsi"));
        words.add(new Word("daughter","tune"));
        words.add(new Word("older brother","taachi"));
        words.add(new Word("younger brother","chalitti"));
        words.add(new Word("older sister","tete"));
        words.add(new Word("younger sister","kolliti"));
        words.add(new Word("grandmother","ama"));
        words.add(new Word("grandfather","paapa"));

        WordAdapter FamilyAdapter = new WordAdapter (this, words);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(FamilyAdapter);
    }
}