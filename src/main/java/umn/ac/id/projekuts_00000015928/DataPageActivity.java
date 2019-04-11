package umn.ac.id.projekuts_00000015928;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataPageActivity extends AppCompatActivity {

    private SharedPreferenceConfig preferenceConfig;

    RecyclerView recyclerView;
    List<Product> productList;
    ProductAdapter adapter;
    EditText isiSearch;

    String DB_NAME = "books.db";

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_page);

        preferenceConfig = new SharedPreferenceConfig(getApplicationContext());

        pref = this.getSharedPreferences("MY_data", MODE_PRIVATE);
        recyclerView = findViewById(R.id.recyclerview);
        showData();
    }

    private void showData(){
        productList = new ArrayList<>();

        recyclerView.setHasFixedSize(true);

        Log.d("debug", "test A");
        DBAdapter dbAdapter = new DBAdapter(getApplicationContext(), DB_NAME, null, 1);
        Log.d("debug", "test B");
        Log.d("debug", "test C");
        String string = "DB: \n";
        Log.d("debug", "test D");
        Cursor cursor = dbAdapter.getAllProducts();
        Log.d("debug", "test E");
        cursor.moveToFirst();
        do{
            //string+= cursor.getString(0) + " - "
            // + cursor.getString(1) + " - "
            // + cursor.getString(2) + "\n";

            productList.add(new Product(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)));

        }while (cursor.moveToNext());
        //displayToast(string);
        dbAdapter.close();

        String mSortSettings = pref.getString("Sort", "ascending");
        if (mSortSettings.equals("ascending")){
            Collections.sort(productList, Product.BY_TITLE_ASCENDING);
        }
        else if (mSortSettings.equals("descending")){
            Collections.sort(productList, Product.BY_TITLE_DESCENDING);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(adapter);
    }

    public void userLogOut(View view) {
        preferenceConfig.writeLoginStatus(false);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void displayToast(String toast){
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.item1){
            showSortDialog();
            return true;
        }
        if(id==R.id.item2){
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        }
        if(id==R.id.item3){
            startActivity(new Intent(this, favorite.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showSortDialog() {
        String[] options = {"By title (Ascending)", "By Title(Descending)"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sort By");
        builder.setIcon(R.drawable.ic_action_sort);
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i==0){ //ascending
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("Sort", "ascending");
                    editor.apply();
                    showData();
                }
                if(i==1){ //descending
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("Sort", "descending");
                    editor.apply();
                    showData();
                }
            }
        });
        builder.create().show();
    }

    public void search(View view){
        isiSearch = findViewById(R.id.isiSearch);

        isiSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }

    private void filter (String text){
        ArrayList<Product> filteredList = new ArrayList<>();

        for(Product item : productList){
            if(item.getTitle().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }

        adapter.filterList(filteredList);
    }


}
