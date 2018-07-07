package com.example.anshulsharma.miwoki;

public class Word {

    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageResourceId;
    private int mSoundId;

    public Word(String defaultTranslations,String miwokTranslation,int soundId){
        mDefaultTranslation=defaultTranslations;
        mMiwokTranslation=miwokTranslation;
        mSoundId=soundId;
    }

    public Word(String defaultTranslations,String miwokTranslation,int ImageResourceId,int soundId){
        mDefaultTranslation=defaultTranslations;
        mMiwokTranslation=miwokTranslation;
        mImageResourceId=ImageResourceId;
        mSoundId=soundId;
    }

    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getImageResourceId(){
        return mImageResourceId;
    }
    public int getmSoundId(){
        return  mSoundId;
    }
}
