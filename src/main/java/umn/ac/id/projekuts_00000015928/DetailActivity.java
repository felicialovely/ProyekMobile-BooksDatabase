package umn.ac.id.projekuts_00000015928;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private TextView TextView_id, TextViewGroup, TextViewFormat,
            TextViewTitle, TextViewAuthor, TextViewPublisher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView_id = (TextView) findViewById(R.id.textViewID);
        TextViewGroup = (TextView) findViewById(R.id.textViewGroup);
        TextViewFormat = (TextView) findViewById(R.id.textViewFormat);
        TextViewTitle = (TextView) findViewById(R.id.textViewTitle);
        TextViewAuthor = (TextView) findViewById(R.id.textViewAuthor);
        TextViewPublisher = (TextView) findViewById(R.id.textViewPublisher);

        // Retrieve data
        Intent intent = getIntent();
        String id = intent.getExtras().getString("ASIN");
        String group = intent.getExtras().getString("Group");
        String format = intent.getExtras().getString("Format");
        String title = intent.getExtras().getString("Title");
        String author = intent.getExtras().getString("Author");
        String publisher = intent.getExtras().getString("Publisher");

        //Setting Value
        TextView_id.setText(id);
        TextViewGroup.setText(group);
        TextViewFormat.setText(format);
        TextViewTitle.setText(title);
        TextViewAuthor.setText(author);
        TextViewPublisher.setText(publisher);

        Button addfavorite = (Button) findViewById(R.id.favorite_button);

        addfavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailActivity.this, favorite.class));
            }

        });

    }
}
