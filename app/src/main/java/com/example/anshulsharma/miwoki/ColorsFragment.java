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
public class ColorsFragment extends android.support.v4.app.Fragment {

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


    public ColorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview=inflater.inflate(R.layout.word_view,container,false);

        ListView listView=rootview.findViewById(R.id.numView);

        numbers.add(new Word("red","wetetti",R.drawable.color_red,R.raw.color_red));
        numbers.add(new Word("green","chokokki",R.drawable.color_green,R.raw.color_green));
        numbers.add(new Word("brown","takaakki",R.drawable.color_brown,R.raw.color_brown));
        numbers.add(new Word("gray","topoppi",R.drawable.color_gray,R.raw.color_gray));
        numbers.add(new Word("black","kululli",R.drawable.color_black,R.raw.color_black));
        numbers.add(new Word("white","kelelli",R.drawable.color_white,R.raw.color_white));
        numbers.add(new Word("dusty yellow","topisse",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        numbers.add(new Word("mustard yellow","chiwiite",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));


        WordAdapter adapter=new WordAdapter(getActivity(),numbers,R.color.colors);
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
