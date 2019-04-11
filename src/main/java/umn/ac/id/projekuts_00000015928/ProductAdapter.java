package umn.ac.id.projekuts_00000015928;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    private Context mCtx;
    private List<Product> productList;

    public ProductAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder (@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout, null);
        ProductViewHolder holder = new ProductViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, final int i) {
        Product product = productList.get(i);
        productViewHolder.bookstitle.setText(product.getTitle());

        productViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //parsing data to the book details
                Intent intent = new Intent(mCtx,DetailActivity.class);
                intent.putExtra("ASIN",productList.get(i).getAsin());
                intent.putExtra("Group",productList.get(i).getGrup());
                intent.putExtra("Format",productList.get(i).getFormat());
                intent.putExtra("Title",productList.get(i).getTitle());
                intent.putExtra("Author",productList.get(i).getAuthor());
                intent.putExtra("Publisher",productList.get(i).getPublisher());

                mCtx.startActivity(intent);
            }
        });

        /*productViewHolder.booksasin.setText(product.getAsin());
        productViewHolder.booksgrup.setText(product.getGrup());
        productViewHolder.booksformat.setText(product.getFormat());
        productViewHolder.booksauthor.setText(product.getAuthor());
        productViewHolder.bookspublisher.setText(product.getPublisher());*/
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void filterList(ArrayList<Product> filteredList){
        productList = filteredList;
        notifyDataSetChanged();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{

        TextView booksasin, booksgrup, booksformat, bookstitle, booksauthor, bookspublisher;
        public View cardView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            bookstitle = itemView.findViewById(R.id.texttitle);
            cardView = (CardView) itemView.findViewById(R.id.cardView_id);

            /*booksasin = itemView.findViewById(R.id.textasin);
            booksgrup = itemView.findViewById(R.id.textgrup);
            booksformat = itemView.findViewById(R.id.textformat);
            booksauthor = itemView.findViewById(R.id.textauthor);
            bookspublisher = itemView.findViewById(R.id.textpublisher);*/
        }
    }
}
