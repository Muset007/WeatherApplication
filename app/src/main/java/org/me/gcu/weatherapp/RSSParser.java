package org.me.gcu.weatherapp;
/*
Mussie Teferi s2038680
 */
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class RSSParser extends AsyncTask<Void, Void, Boolean> {

    private final Context context;
    private final InputStream inputStream;
    private final RecyclerView rv;

    private ProgressDialog pd;
    private final ArrayList<RSSItemParse> RSSItemParses = new ArrayList<>();

    public RSSParser(Context c, InputStream is, RecyclerView rv) {
        this.context = c;
        this.inputStream = is;
        this.rv = rv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(context);
        pd.setTitle("Parse Data");
        pd.setMessage("Parsing data, Please wait");
        pd.show();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        return this.parseRSS();
    }

    @Override
    protected void onPostExecute(Boolean isParsed) {
        super.onPostExecute(isParsed);

        pd.dismiss();
        if (isParsed) {
            //BIND
            rv.setAdapter(new RSSItemAdapter(RSSItemParses, context));
        } else {
            Toast.makeText(context, "Unable To Parse", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parseRSS() {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();

            parser.setInput(inputStream, null);
            String currentItem = null;
            RSSItemParses.clear();

            RSSItemParse RSSItemParse = new RSSItemParse();
            int event = parser.getEventType();
            //Boolean isWebsiteTitle = true;

            do {
                String name = parser.getName();

                switch (event) {
                    case XmlPullParser.START_TAG:
                        if (name.equals("item")) {
                            RSSItemParse = new RSSItemParse();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        currentItem = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (name.equals("title")) {
                            String[] array1 = currentItem.split("[,]", 2);
                            //for (String t : array1) {
                                //System.out.println(t);
                                RSSItemParse.setTitle(array1[0]);
                            //}
                            //parseItem.setTitle(currentItem);
                        } else if (name.equals("description")) {
                            RSSItemParse.setDescription(currentItem);
                        } else if (name.equals("pubDate")) {
                            RSSItemParse.setDate(currentItem);
                        } else if (name.equals("link")) {
                            RSSItemParse.setLink(currentItem);
                        }

                        if (name.equals("item")) {
                            RSSItemParses.add(RSSItemParse);
                        }
                        break;
                }

                event = parser.next();

            } while (event != XmlPullParser.END_DOCUMENT);

            return true;

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

}

