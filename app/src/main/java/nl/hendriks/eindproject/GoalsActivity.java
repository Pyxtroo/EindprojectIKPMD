package nl.hendriks.eindproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GoalsActivity extends AppCompatActivity {

    RecyclerView recyclerGoals;
    ArrayList<Goal> GoalsList = new ArrayList<>();
    Button buttontest;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doelen);

        recyclerGoals = findViewById(R.id.recycler_doelen);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerGoals.setLayoutManager(layoutManager);

        GoalsList = (ArrayList<Goal>) getIntent().getExtras().getSerializable("list");

        recyclerGoals.setAdapter(new GoalAdapter(GoalsList, getApplicationContext()));

    }

    public void LoadAddGoalActivity(View x){
        Intent i = new Intent(this, AddGoalActivity.class);
        startActivity(i);

    }

}
