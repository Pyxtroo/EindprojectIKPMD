package nl.hendriks.eindproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GoalsActivity extends AppCompatActivity {

    RecyclerView recyclerGoals;
    ArrayList<Goal> GoalsList = new ArrayList<>();
    Button buttontest;

    Button firstFragmentbtn;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.Login:
                Toast.makeText(this, "Uitgelogd", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, loginActivity.class);
                startActivity(i);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doelen);

        recyclerGoals = findViewById(R.id.recycler_doelen);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerGoals.setLayoutManager(layoutManager);

        GoalsList = (ArrayList<Goal>) getIntent().getExtras().getSerializable("list");

        recyclerGoals.setAdapter(new GoalAdapter(GoalsList, getApplicationContext()));

        firstFragmentbtn = findViewById(R.id.FirstFragment);


        firstFragmentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                replaceFragment(new Piechart(GoalsList,getApplicationContext()));

            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Piechart, fragment);
        fragmentTransaction.commit();


    }

    public void LoadAddGoalActivity(View x) {
        Intent i = new Intent(this, AddGoalActivity.class);
        startActivity(i);

    }

}
