package org.me.gcu.weatherapp;
/*
Mussie Teferi s2038680
 */
public class AppMenuItem {

    private final int mImageResource;
    private final String mText1;
    private final String mText2;

    public AppMenuItem(int imageResource, String text1, String text2) {
        mImageResource = imageResource;
        mText1 = text1;
        mText2 = text2;
    }

    public int getImageResource(){
        return mImageResource;
    }

    public String getText1(){
        return mText1;
    }

    public String getText2(){
        return mText2;
    }
}
