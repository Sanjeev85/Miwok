package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Colors extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("red","wetetti"));
        words.add(new Word("green","chokokki"));
        words.add(new Word("brown","takaaki"));
        words.add(new Word("gray","topoppi"));
        words.add(new Word("black","kululli"));
        words.add(new Word("white","kelelli"));
        words.add(new Word("dusty yellow","topiisa"));
        words.add(new Word("mustard yellow","chiwiita"));

        WordAdapter ColorAdapter = new WordAdapter (this, words);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(ColorAdapter);
    }
}