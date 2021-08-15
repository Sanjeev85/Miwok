package com.example.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Family_Members extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

    /**
     * Audio focus listener so that we can catch when request accepted
     */
    AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            //temporary pause the audio as audio focus gone temporarily
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {

                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);//start audio from beginning
            }
            //As our playback is end or we lost audio focus for
            // indefinite amount of time
            else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
            //We gain audio focus for indefinite amount of time
            else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mMediaPlayer.start();
            }
        }
    };

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("FamilyActivity", "onStop()");
        releaseMediaPlayer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //create audio manager to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("father", "әpә", R.drawable.family_father, R.raw.family_father));
        words.add(new Word("mother", "әṭa", R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word("son", "angsi", R.drawable.family_son, R.raw.family_son));
        words.add(new Word("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word("older brother", "taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word("younger brother", "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        words.add(new Word("older sister", "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Word("younger sister", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word("grandmother ", "ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Word("grandfather", "paapa", R.drawable.family_grandfather, R.raw.family_grandfather));


        WordAdapter FamilyAdapter = new WordAdapter(this, R.color.category_family, words);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(Numbers.this, "List item clicked", Toast.LENGTH_SHORT).show();

                //first audio file
                //this will work when in between another audio is played before completion of
                /** Release the media player if it currently exists because we are about to
                 play a different sound file */
                releaseMediaPlayer();
                Word word = words.get(position);


                /**Requestion audio focus
                 * @param1 AudioFocusChange listener
                 * @param2 Type of Audio
                 * @param3 duration required to play audio*/
                int result = mAudioManager.requestAudioFocus(mAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                /**If audio focus request granted we play our audio */
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    //audio focus comes to our app and we create object of media player and play audio
                    mMediaPlayer = MediaPlayer.create(Family_Members.this, word.getmAudioResourceId());
                    mMediaPlayer.start();

                    //this will work only when song is over
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
        listView.setAdapter(FamilyAdapter);
    }

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            mMediaPlayer = null;

            //abandon audio focus when activity is stopped
            mAudioManager.abandonAudioFocus(mAudioFocusChangeListener);
        }
    }
}