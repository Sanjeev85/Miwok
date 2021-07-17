package com.example.miwok;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Numbers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        ArrayList<String> words = new ArrayList<String>();
        words.add("one");
        words.add("two");
        words.add("three");
        words.add("four");
        words.add("five");
        words.add("six");
        words.add("seven");
        words.add("eight");
        words.add("nine");
        words.add("ten");
        //log values or arraylist in logcat
        for (String i : words) {
            Log.v("NumberActivity", i);
        }
        //parent View
//        LinearLayout numbersXml = (LinearLayout) findViewById(R.id.numbersXml);
        //child view of linear layour
/*        TextView wordView = new TextView(this);//context -> this
        //set text of wordView to 0th index of arraylist
        wordView.setText(words.get(0));
        //add wordView to linear layout to display
        numbersXml.addView(wordView);

        for(int i = 0 ; i < words.size() ; i++){
            //create new textview
            TextView wordView = new TextView(this);
            //set text to current index
            wordView.setText(words.get(i));
            //Add this textView as another child to linear layout
            numbersXml.addView(wordView);
        }*/

        /* Array Adapter use for recycling view*/
        ArrayAdapter<String> itemsAdapter
                = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

    }
}