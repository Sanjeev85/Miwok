package com.example.miwok;

import android.os.Bundle;
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
        // Create a list of words
        words.add(new Word("one", "lutti", R.drawable.number_one));
        words.add(new Word("two", "otiiko", R.drawable.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four));
        words.add(new Word("five", "massokka", R.drawable.number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight));
        words.add(new Word("nine", "wo’e", R.drawable.number_nine));
        words.add(new Word("ten", "na’aacha", R.drawable.number_ten));

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
        WordAdapter itemsAdapter = new WordAdapter(this, R.color.category_numbers, words);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

    }
}