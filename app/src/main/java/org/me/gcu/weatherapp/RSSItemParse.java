package org.me.gcu.weatherapp;
/*
Mussie Teferi s2038680
 */
public class RSSItemParse {

    private int mImageResource;
    private String mLocationName;
    private String mMaxTemperature;
    private String mMinTemperature;
    private String mWindDir;
    private String mWindSpeed;
    private String mDay;
    private String mDate;
    private String mRain;
    private String mTitle;
    private String mLink;
    private String mDescription;

    public RSSItemParse() {
    }

    public RSSItemParse(String mLocationName, String mMaxTemperature, String mMinTemperature, String mDate) {
        //this.mImageResource = mImageResource;
        this.mLocationName = mLocationName;
        this.mMaxTemperature = mMaxTemperature;
        this.mMinTemperature = mMinTemperature;
        this.mDate = mDate;
    }


    /////GETTERS
    public String getLocationName() {
        return mLocationName;
    }
    public int getImageResource() {
        return mImageResource;
    }
    public String getWindDir() {
        return mWindDir;
    }
    public String getWindSpeed() {
        return mWindSpeed;
    }
    public String getMaxTemperature() {
        return mMaxTemperature;
    }
    public String getMinTemperature() {
        return mMinTemperature;
    }
    public String getRain() {
        return mRain;
    }
    public String getDate() {
        return mDate;
    }
    public String getDay() {
        return mDay;
    }
    public String getTitle() {
        return mTitle;
    }
    public String getLink() {
        return mLink;
    }
    public String getDescription() {
        return mDescription;
    }



    /////SETTERS
    public void setImageResource(int mImageResource) {
        this.mImageResource = mImageResource;
    }
    public void setLocationName(String mLocationName) {
        this.mLocationName = mLocationName;
    }
    public void setTitle(String title) {
        this.mTitle = title;
    }
    public void setWindSpeed(String mWindSpeed) {
        this.mWindSpeed = mWindSpeed;
    }
    public void setMaxTemperature(String mMaxTemperature) {
        this.mMaxTemperature = mMaxTemperature;
    }
    public void setMinTemperature(String mMinTemperature) {
        this.mMinTemperature = mMinTemperature;
    }
    public void setWindDir(String mWindDir) {
        this.mWindDir = mWindDir;
    }
    public void setDay(String mDay) {
        this.mDay = mDay;
    }
    public void setDate(String mDate) {
        this.mDate = mDate;
    }
    public void setRain(String rain) {
        this.mRain = rain;
    }
    public void setLink(String link){this.mLink = link;}

    public void setDescription(String description) {
        this.mDescription = description;
    }
}
