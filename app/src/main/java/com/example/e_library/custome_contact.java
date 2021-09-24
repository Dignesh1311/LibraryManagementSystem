package com.example.e_library;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

class custome_contact extends BaseAdapter
{
    Context context;
    int [] image;
    String [] names;
    LayoutInflater inflater;

    public custome_contact(ContactUs contactUs, int[] image, String[] name)
    {
        context=contactUs;
        this.image=image;
        this.names=name;
        inflater=LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return names.length;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View view= inflater.inflate(R.layout.custome_contact,null);

        LinearLayout linearLayout=view.findViewById(R.id.contact_linear);
        ImageView imageView=view.findViewById(R.id.contact_image);
        TextView textView=view.findViewById(R.id.contact_textview);

        imageView.setImageResource(image[position]);
        textView.setText(names[position]);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position == 0){
                    Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:4343434343°"));
                    context.startActivity(intent);
                }
                else if(position == 1){
                    Intent intent=new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com"));
                    context.startActivity(intent);
                }else if(position == 2){
                    Intent intent=new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com"));
                    context.startActivity(intent);
                }else if(position == 3){
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto","librarymanagement436@gmail.com", null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                    context.startActivity(Intent.createChooser(emailIntent, "Send email..."));
                }
            }
        });

        return view;
    }
}