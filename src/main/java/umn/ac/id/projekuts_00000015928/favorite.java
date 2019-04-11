package umn.ac.id.projekuts_00000015928;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class favorite extends AppCompatActivity {
    private RecyclerView recyclerView1;
    private favoriteadapter adapter2;
    private List<Product> favoritelist;
    private ActionMenuView amvMenu;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(favorite.this, LoginActivity.class);
        startActivity(intent);

        return true;

    }

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.list_favorite);

        Toolbar t = (Toolbar) findViewById(R.id.tToolbar);

        amvMenu = (ActionMenuView) findViewById(R.id.amvMenu);

        amvMenu.setOnMenuItemClickListener(new ActionMenuView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return onOptionsItemSelected(menuItem);
            }
        });

        //setSupportActionBar(t);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        favoritelist = new ArrayList<>();
        recyclerView1 = findViewById(R.id.recycler_view1);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        DBAdapterFavorite dbAdapter = new DBAdapterFavorite(getApplicationContext(),null,null,1);
        Cursor cursor = dbAdapter.getAllBooks();
        Log.d("DEBUGDB","VALUE CURSOR : " + cursor);
        cursor.moveToFirst();
        if(cursor.getCount() < 1){
            Log.d("DEBUGDB","CURSOR KOSONG");
        }
        else{
            do{
                favoritelist.add(
                        new Product(
                                cursor.getString(0),
                                cursor.getString(1)
                        )
                );
            }while (cursor.moveToNext());
        }
        cursor.close();
        adapter2 = new favoriteadapter(this,favoritelist);
        recyclerView1.setAdapter(adapter2);
        dbAdapter.close();
    }
}
