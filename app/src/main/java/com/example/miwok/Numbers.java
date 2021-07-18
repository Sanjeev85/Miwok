package com.example.miwok;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Numbers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        /*We have to pass 2 list but array adapter only supports one list inside constructor
            that's why we have created Word class and now we are adding abjects of words to
            Arraylist and pass it to array adapter
        * */


//        ArrayList<String> words = new ArrayList<String>();
        ArrayList<Word> words = new ArrayList<>();

        //words.add("one"); to add words to arraylist
//        Word  w = new Word("one", "lutti");
//        words.add(w);
        //or we can add it in more concise  way
        words.add(new Word("one","lutti"));
        words.add(new Word("two","otiiko"));
        words.add(new Word("three","tolookosu"));
        words.add(new Word("four","oyyisa"));
        words.add(new Word("five","massokka"));
        words.add(new Word("six","temmokka"));
        words.add(new Word("seven","kenekaku"));
        words.add(new Word("eight","kawinta"));
        words.add(new Word("nine","wo'e"));
        words.add(new Word("ten","na'aacha"));

        //log values or arraylist in logcat
//        for (Word i : words) {
//            Log.v("NumberActivity", String.valueOf(i));
//        }
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
        WordAdapter itemsAdapter = new WordAdapter (this, words);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

    }
}