package com.example.anshulsharma.miwoki;


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
public class FamilyFragment extends android.support.v4.app.Fragment{

    ArrayList<Word> numbers=new ArrayList<>();
    MediaPlayer mediaPlayer;

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

    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview=inflater.inflate(R.layout.word_view,container,false);

        ListView listView=rootview.findViewById(R.id.numView);

        numbers.add(new Word("father","ape",R.drawable.family_father,R.raw.family_father));
        numbers.add(new Word("mother","eta",R.drawable.family_mother,R.raw.family_mother));
        numbers.add(new Word("son","angsi",R.drawable.family_son,R.raw.family_son));
        numbers.add(new Word("daughter","tune",R.drawable.family_daughter,R.raw.family_daughter));
        numbers.add(new Word("older brother","taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        numbers.add(new Word("younger brother","chalitti",R.drawable.family_younger_brother,R.raw.family_younger_sister));
        numbers.add(new Word("older sister","tete",R.drawable.family_older_sister,R.raw.family_older_sister));
        numbers.add(new Word("younger sister","kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        numbers.add(new Word("grandmother","ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        numbers.add(new Word("grandfather","paapa",R.drawable.family_grandmother,R.raw.family_grandfather));


        WordAdapter adapter=new WordAdapter(getActivity(),numbers,R.color.family);
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
