package com.example.anshulsharma.miwoki;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WordAdapter extends ArrayAdapter<Word>{

    int mColorResourceId;


    public WordAdapter(@NonNull Context context, ArrayList<Word>words,int colorResourceId) {
        super(context, 0,words);
        mColorResourceId=colorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView=convertView;
        if(listItemView==null){
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.listview,parent,false);
        }

        final Word currentWord=getItem(position);

        TextView miwokTextView=listItemView.findViewById(R.id.miwok);
        miwokTextView.setText(currentWord.getmMiwokTranslation());

        TextView defaultTextView=listItemView.findViewById(R.id.english);
        defaultTextView.setText(currentWord.getmDefaultTranslation());

        ImageView imageView=listItemView.findViewById(R.id.imageView3);
        if(currentWord.getImageResourceId()==0){
            imageView.setVisibility(View.GONE);
        }else {
            imageView.setImageResource(currentWord.getImageResourceId());
        }

        View textContainer=listItemView.findViewById(R.id.container);
        int color= ContextCompat.getColor(getContext(),mColorResourceId);
        textContainer.setBackgroundColor(color);


        return  listItemView;


    }
}
