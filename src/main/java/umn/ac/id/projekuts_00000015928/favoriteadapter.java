package umn.ac.id.projekuts_00000015928;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class favoriteadapter extends RecyclerView.Adapter<favoriteadapter.BookViewHolder2> {
    private Context context;
    private List<Product> favoritelist;

    public favoriteadapter(Context context,List<Product> favoritelist){
        this.context = context;
        this.favoritelist = favoritelist;
    }

    @NonNull
    @Override
    public BookViewHolder2 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_favorite,viewGroup,false);
        BookViewHolder2 holder = new BookViewHolder2(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BookViewHolder2 bookViewHolder2,final int i){
        final Product books = favoritelist.get(i);
        bookViewHolder2.bookTitle.setText(books.getTitle());
        bookViewHolder2.bookAuthor.setText(books.getAuthor());
    }

    @Override
    public int getItemCount() {return favoritelist.size();}

    public static class BookViewHolder2 extends RecyclerView.ViewHolder{
        TextView bookTitle, bookAuthor;

        public BookViewHolder2(@NonNull View itemView){
            super(itemView);
            bookTitle = itemView.findViewById(R.id.textViewTitle);
            bookAuthor = itemView.findViewById(R.id.textViewAuthor);
        }
    }

}
