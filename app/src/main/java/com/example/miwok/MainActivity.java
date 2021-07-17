package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find textview numbers
        TextView numbers = (TextView) findViewById(R.id.numbers);
        //set onclickListener on that view
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent numberIntent = new Intent(MainActivity.this, Numbers.class);
                startActivity(numberIntent);
            }
        });

        //find textView colors
        TextView colors = (TextView) findViewById(R.id.colors);
        //set onclickListener on colors view
        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent colorsIntent = new Intent(MainActivity.this, Colors.class);
                startActivity(colorsIntent);
            }
        });
        //find textView family
        TextView family_members = (TextView) findViewById(R.id.family);
        //set onclickListener on that view
        family_members.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent familyIntent = new Intent(MainActivity.this, Family_Members.class);
                startActivity(familyIntent);
            }
        });

        //find textview phrases
        TextView phrases = (TextView) findViewById(R.id.phrases);
        //set onclickListener on phrases view
        phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phrase = new Intent(MainActivity.this, Phrases.class);
                startActivity(phrase);
            }
        });
    }
}