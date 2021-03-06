package com.example.anshulsharma.miwoki;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumberFragment extends android.support.v4.app.Fragment {

    ArrayList<Word> numbers=new ArrayList<>();

    MediaPlayer mediaPlayer;

    public AudioManager mAudioManager;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };




    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }


    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }


    public NumberFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview=inflater.inflate(R.layout.word_view,container,false);

        mAudioManager=(AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);


        ListView listView=rootview.findViewById(R.id.numView);

        numbers.add(new Word("one","lutti",R.drawable.number_one,R.raw.number_one));
        numbers.add(new Word("two","otiiko",R.drawable.number_two,R.raw.number_two));
        numbers.add(new Word("three","tolookosu",R.drawable.number_three,R.raw.number_three));
        numbers.add(new Word("four","oyyisa",R.drawable.number_four,R.raw.number_four));
        numbers.add(new Word("five","massokka",R.drawable.number_five,R.raw.number_five));
        numbers.add(new Word("six","temmokka",R.drawable.number_six,R.raw.number_six));
        numbers.add(new Word("seven","kenekaku",R.drawable.number_seven,R.raw.number_seven));
        numbers.add(new Word("eight","kawinta",R.drawable.number_eight,R.raw.number_eight));
        numbers.add(new Word("nine","wo'e",R.drawable.number_nine,R.raw.number_nine));
        numbers.add(new Word("ten","na'aacha",R.drawable.number_ten,R.raw.number_ten));


        WordAdapter adapter=new WordAdapter(getActivity(),numbers,R.color.numbers);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word a=numbers.get(i);
                releaseMediaPlayer();

                int result=mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mediaPlayer = MediaPlayer.create(getActivity(), a.getmSoundId());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            releaseMediaPlayer();
                        }
                    });

                }
            }
        });



        return rootview;
    }

}
