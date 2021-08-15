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

public class Phrases extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    //Handles audio focus when playing a sound file
    private AudioManager mAudioManager;
    /**This is an event listener whenever audio focus changes it will return a constant
     * value which will we compare in the overridden method onAudioFocusChange()
     *
     * */
//    AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
//        @Override
//        public void onAudioFocusChange(int focusChange) {
//
//            if(focusChange == AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
//            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
//                /** Pause playback because your Audio Focus was
//                    temporarily stolen, but will be back soon.
//                    i.e. for a phone call */
//            }
//            else if(focusChange == AudioManager.AUDIOFOCUS_LOSS) {
//                /** Stop playback because you lost the audio focus
//                 * i.e. the user started some other playback app
//                 * remember to unregister your controls/ buttons
//                 * and release audio focus!
//                 * you're done.
//                 * */
//                mAudioManager.abandonAudioFocus(audioFocusChangeListener);
//                // this will release audio focus
//            }
//            else if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
//                /**Lower the volume, because something else is also
//                 * playing audio over you.
//                 * i.e. for notifications or navigation directions
//                 * Depending on your audio playback, you may prefer
//                 * to pause playback here instead.
//                 * */
//            }
//            else if(focusChange == AudioManager.AUDIOFOCUS_GAIN) {
//                /**Resume playback, because you hold the audio focus
//                 * again!
//                 * i.e. the phone call ended or the nav directions
//                 * are finished
//                 * If your implement ducking and lower the volume, be
//                 * sure to return it to normal here(when call ended), as well.
//                 * */
//            }
//        }
//    };


    /**
     * Adding audio focus listener
     */
    AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            //pause playback
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                /**if we lost audio focus for some reason then
                 * we pause playback */
                mMediaPlayer.pause();
                /**Since pronounciation is very short no need to
                 * start in between. We start from beginning */
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                /**Play audio as we audio focus is assigned to us
                 * So we resume playback*/
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                /**We have lost the audio focus now we can free the resources
                 * which are utilised by media player and stop playback*/
                releaseMediaPlayer();
            }
        }
    };


    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("PhrasesActivity", "onStop()");
        releaseMediaPlayer();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


        final ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?", "tinna oyaase'ne", R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...", "auirset", R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?", "michekses?", R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I'm feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?", "eenes'aa?", R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I'am coming.", "eenem", R.raw.phrase_im_coming));
        words.add(new Word("Let's go.", "yoowutis", R.raw.phrase_lets_go));
        words.add(new Word("Come here.", "enni'nem", R.raw.phrase_come_here));

        WordAdapter PhrasesAdapter = new WordAdapter(this, R.color.category_phrases, words);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Release the media player if it currently exists because we are about to
                // play a different sound file
                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();
                Word word = words.get(position);


                /**Requesting audio focus*/
                int result = mAudioManager.requestAudioFocus(mAudioFocusChangeListener,
                        //Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        //Reques permanent focus
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                //if audio focus granted
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    //We got audio focus now we can play audio

                    //Audio play start
                    mMediaPlayer = MediaPlayer.create(Phrases.this, word.getmAudioResourceId());
                    mMediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }


            }
        });
        listView.setAdapter(PhrasesAdapter);
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            /**Abandon audio focus if app in stop state or our playback is over */
            mAudioManager.abandonAudioFocus(mAudioFocusChangeListener);
        }
    }
}