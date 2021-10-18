package nl.hendriks.eindproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void launchLogin(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);                           // laad pagina i
    }

    public void checkRegister(View y){
        launchLogin(y);
    }
}