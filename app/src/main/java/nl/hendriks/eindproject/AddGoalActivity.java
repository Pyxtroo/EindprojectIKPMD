package nl.hendriks.eindproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddGoalActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout layoutList;
    Button buttonAdd;
    Button buttonDoelToevoegen;


    List<String> frequencyList = new ArrayList<>();
    private ArrayList GoalsList = new ArrayList<Goal>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        layoutList = findViewById(R.id.layout_list);
        buttonAdd = findViewById(R.id.button_add);
        buttonDoelToevoegen = findViewById(R.id.ButtonUpdateGoals);


        buttonAdd.setOnClickListener(this);
        buttonDoelToevoegen.setOnClickListener(this);


        loadData();


        frequencyList = Arrays.asList(getResources().getStringArray(R.array.Repeat_Goal));


    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("sharedpr", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(GoalsList);
        editor.putString("task list", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("sharedpr", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<Goal>>() {
        }.getType();
        GoalsList = gson.fromJson(json, type);

        if (GoalsList == null) {
            GoalsList = new ArrayList<Goal>();
        }


    }


    @Override //wordt gerunt als je op de add button klikt
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.button_add:  //voegt view toe als er op de add button wordt geklikt

                addView();

                break;

            case R.id.ButtonUpdateGoals: //als data klopt wordt er een nieuwe view gemaakt

                if (checkIfValidAndRead()) {

                    Intent intent = new Intent(AddGoalActivity.this, GoalsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("list", GoalsList);
                    intent.putExtras(bundle);

                    startActivity(intent);


                }
                break;

        }

    }


    private void removeView(View view) {

        layoutList.removeView(view);

    }

    private boolean checkIfValidAndRead() {

        boolean result = true;

        for (int i = 0; i < layoutList.getChildCount(); i++) {

            View goalView = layoutList.getChildAt(i);

            EditText editTextName = (EditText) goalView.findViewById(R.id.edit_goal_name);
            AppCompatSpinner spinnerTeam = (AppCompatSpinner) goalView.findViewById(R.id.spinner_team);

            Goal goal = new Goal();

            if (!editTextName.getText().toString().equals("")) {
                goal.setGoalName(editTextName.getText().toString());
            } else {
                result = false;
                break;
            }

            if (spinnerTeam.getSelectedItemPosition() != 0) {
                goal.setGoalFrequency(frequencyList.get(spinnerTeam.getSelectedItemPosition()));
            } else {
                result = false;
                break;
            }

            GoalsList.add(goal);
            saveData();

        }

        if (GoalsList.size() == 0) {
            result = false;
            Toast.makeText(this, "Voer doel in!", Toast.LENGTH_SHORT).show();
        } else if (!result) {
            Toast.makeText(this, "Voer alle data correct", Toast.LENGTH_SHORT).show();
        }


        return result;
    }

    private void addView() {
        final View goalView = getLayoutInflater().inflate(R.layout.rij_add_goal, null, false);



        EditText editText = (EditText) goalView.findViewById(R.id.edit_goal_name);
        AppCompatSpinner spinnerTeam = (AppCompatSpinner) goalView.findViewById(R.id.spinner_team);
        ImageView imageClose = (ImageView) goalView.findViewById(R.id.image_remove);


        imageClose.setOnClickListener(new View.OnClickListener() { //word aangeroepen als je op het kruis klikt

            @Override
            public void onClick(View v) {
                removeView(goalView);
            }
        });

        layoutList.addView(goalView);

    }
}