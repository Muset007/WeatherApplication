package org.me.gcu.weatherapp;
/*
Mussie Teferi s2038680
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RSSItemAdapter extends RecyclerView.Adapter<RSSItemAdapter.ViewHolder> {

    private final ArrayList<RSSItemParse> RSSItemParses;
    private final Context context;

    public RSSItemAdapter(ArrayList<RSSItemParse> RSSItemParses, Context context){
        this.RSSItemParses = RSSItemParses;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.alternative_content, parent, false); //parent.getContext()
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RSSItemParse currentItem = RSSItemParses.get(position);

        // Assuming currentItem.getTitle() returns the location, and currentItem.getDescription() returns date followed by the weather data
        String title = currentItem.getTitle(); // e.g., "Glasgow"
        String description = currentItem.getDescription(); // e.g., "11°C, Wind: N 9 mph, ..."

        // Splitting the description to extract date and weather details separately
        String[] parts = description.split(", ");
        String date = parts[0]; // This assumes that the date is the first part before the comma
        String weatherDetails = description.substring(date.length() + 2); // This gets the rest of the weather details

        holder.tv_Location.setText(title);
        holder.tv_Date.setText(date);

        // Format weather details with new lines
        String formattedWeatherDetails = weatherDetails.replace(", ", "\n");

        holder.tv_minTemp.setText(formattedWeatherDetails);

        // Set additional data like the date it was published
        holder.tv_maxTemp.setText("Data published on: " + currentItem.getDate());
    }


    @Override
    public int getItemCount() {
        return RSSItemParses.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        //public ImageView mImageView;
        public TextView tv_Location;
        public TextView tv_Date;
        public TextView tv_minTemp;
        public TextView tv_maxTemp;

        public ViewHolder( View itemView) {
            super(itemView);
            //mImageView = itemView.findViewById(R.id.imageView);
            tv_Location = itemView.findViewById(R.id.tv_Location);
            tv_Date = itemView.findViewById(R.id.tv_Date);
            tv_minTemp = itemView.findViewById(R.id.tv_minTemp);
            tv_maxTemp = itemView.findViewById(R.id.tv_maxTemp);
        }
    }
}
