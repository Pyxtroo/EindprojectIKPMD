package nl.hendriks.eindproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this, "Firebase connection succesful", Toast.LENGTH_LONG).show();
    }

    public void loadRegisterScreen(View y){
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);                           // laad pagina i
    }

    public void loadtest(View x){
        Intent i = new Intent(this, AddGoalActivity.class);
        startActivity(i);
    }
}