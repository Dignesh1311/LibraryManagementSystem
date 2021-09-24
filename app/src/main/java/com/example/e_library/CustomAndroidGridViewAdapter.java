package com.example.e_library;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomAndroidGridViewAdapter  extends BaseAdapter
{
    private Context mContext;
    private final String[] string;
    private final int[] Imageid;


    public CustomAndroidGridViewAdapter(Context c,int[] Imageid,String[] string ) {
        mContext = c;
        this.Imageid = Imageid;
        this.string = string;
    }

    @Override
    public int getCount() {
        return string.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        View grid;
        Context context;
        LayoutInflater inflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView==null)
        {

            grid=new View(mContext);
            grid=inflater.inflate(R.layout.custome_grid_layout,null);
            ImageView  imageView=(ImageView)grid.findViewById(R.id.gridview_image);
            TextView textView=(TextView)grid.findViewById(R.id.gridview_text);
            imageView.setImageResource(Imageid[position]);
            textView.setText(string[position]);
            LinearLayout linearLayout=grid.findViewById(R.id.liner_layout_gridview);
           // GridView gridView=grid.findViewById(R.id.grid_view);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    if(position == 0){
                        Intent intent = new Intent(mContext, Profile.class);
                        mContext.startActivity(intent);
                    }
                    if(position == 1){
                        Intent intent = new Intent(mContext, Book.class);
                        mContext.startActivity(intent);
                    }
                    if(position == 2){
                        Intent intent = new Intent(mContext,Book_read.class);
                        mContext.startActivity(intent);

                    }
                    if(position == 3){
                        Intent intent = new Intent(mContext,Book_status.class);
                        mContext.startActivity(intent);

                    }

                    if(position == 4){
                        Intent intent = new Intent(mContext, ContactUs.class);
                        mContext.startActivity(intent);
                    }
                    if(position==5)
                    {
                        Intent intent = new Intent(mContext, Feedback.class);
                        mContext.startActivity(intent);

                    }

                }
            });
        }
        else
        {
            grid=(View) convertView;


        }

        return grid;
    }
}
