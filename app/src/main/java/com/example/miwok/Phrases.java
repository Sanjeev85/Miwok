package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Phrases extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

         ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("Where are you going?","minto wuksus"));
        words.add(new Word("What is your name?","tinna oyaase'ne"));
        words.add(new Word("My name is...","michekses?"));
        words.add(new Word("How are you feeling?","michekses?"));
        words.add(new Word("I'm feeling good.","kuchi achit"));
        words.add(new Word("Are you coming?","eenes'aa?"));
        words.add(new Word("Yes, I'am coming.","eenem"));
        words.add(new Word("Let's go.","yoowutis"));
        words.add(new Word("Come here.","enni'nem"));

        WordAdapter PhrasesAdapter = new WordAdapter (this, R.color.category_phrases, words);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(PhrasesAdapter);
    }
}