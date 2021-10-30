package nl.hendriks.eindproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GoalsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);
    }

    public void loadAddGoalsScreen(View v){
        System.out.println("test method");
        Intent i = new Intent(this, AddGoalsActivity.class);
        startActivity(i);                           // laad pagina i
    }

}