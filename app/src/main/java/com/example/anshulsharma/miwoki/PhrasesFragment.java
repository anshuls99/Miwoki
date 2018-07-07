package com.example.anshulsharma.miwoki;


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
public class PhrasesFragment extends android.support.v4.app.Fragment {

    ArrayList<Word> numbers=new ArrayList<>();
    MediaPlayer mediaPlayer;

    public AudioManager mAudioManager;

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview=inflater.inflate(R.layout.word_view,container,false);

        ListView listView=rootview.findViewById(R.id.numView);

        numbers.add(new Word("Where are you going?","minto wuksus",R.raw.phrase_where_are_you_going));
        numbers.add(new Word("What is your name?","tinnә oyaase'nә",R.raw.phrase_what_is_your_name));
        numbers.add(new Word("My name is...","oyaaset...",R.raw.phrase_my_name_is));
        numbers.add(new Word("How are you feeling?","michәksәs?",R.raw.phrase_how_are_you_feeling));
        numbers.add(new Word("I’m feeling good.","kuchi achit",R.raw.phrase_im_feeling_good));
        numbers.add(new Word("Are you coming?","әәnәs'aa?",R.raw.phrase_are_you_coming));
        numbers.add(new Word("Yes, I’m coming.","hәә’ әәnәm",R.raw.phrase_yes_im_coming));
        numbers.add(new Word("I’m coming.","әәnәm",R.raw.phrase_im_coming));
        numbers.add(new Word("Let’s go.","yoowutis",R.raw.phrase_lets_go));
        numbers.add(new Word("Come here","әnni'nem",R.raw.phrase_come_here));


        WordAdapter adapter=new WordAdapter(getActivity(),numbers,R.color.phrases);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word a=numbers.get(i);
                mediaPlayer= MediaPlayer.create(getActivity(),a.getmSoundId());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        releaseMediaPlayer();
                    }
                });
            }
        });

        return rootview;
    }

}
