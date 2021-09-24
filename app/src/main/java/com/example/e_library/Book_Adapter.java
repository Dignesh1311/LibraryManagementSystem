package com.example.e_library;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Book_Adapter extends RecyclerView.Adapter<Book_Adapter.ViewHolder>
{
     private Book_book[] book;
    static Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        context=parent.getContext();
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Book_Adapter.ViewHolder holder, int position)
    {
        holder.book_Name.setText(book[position].getBook_name());
        holder.book_Author.setText(book[position].getBook_Author());
        holder.logo.setImageResource(book[position].getLogo());

    }

    @Override
    public int getItemCount() {
        return book.length;
    }

    public void setItems(Book_book[] book) {
        this.book =book;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView book_Author;
        TextView book_Name;
        ImageView logo;
        Button btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            book_Name=itemView.findViewById(R.id.Book_Name);
            book_Author=itemView.findViewById(R.id.Book_Authors);
            logo=itemView.findViewById(R.id.logo);
            btn=itemView.findViewById(R.id.Book_request);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent=new Intent(context,Book_essus_form.class);
                    context.startActivity(intent);
                }
            });



        }


    }

}