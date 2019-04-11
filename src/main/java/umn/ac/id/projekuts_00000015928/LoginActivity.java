package umn.ac.id.projekuts_00000015928;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferenceConfig preferencesConfig;
    private EditText UserName, UserPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        preferencesConfig = new SharedPreferenceConfig(getApplicationContext());

        UserName = findViewById(R.id.user_name);
        UserPassword = findViewById(R.id.user_password);

        //jika udah login, maka otomatis masuk
        if(preferencesConfig.readLoginStatus()){
            startActivity(new Intent(this, DataPageActivity.class));
            finish();
        }
    }

    public void LoginUser(View view) {
        String username = UserName.getText().toString();
        String userpassword = UserPassword.getText().toString();

        //cek untuk login
        if(username.equals(getResources().getString(R.string.user_name)) && userpassword.equals(getResources().getString(R.string.user_password))){
            startActivity(new Intent(this, DataPageActivity.class));
            preferencesConfig.writeLoginStatus(true);
            finish();
        }
        else {
            Toast.makeText(this,"Login failed",Toast.LENGTH_SHORT).show();
            UserName.setText("");
            UserPassword.setText("");
        }
    }
}