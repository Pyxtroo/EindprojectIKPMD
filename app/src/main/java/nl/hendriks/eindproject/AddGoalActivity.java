package nl.hendriks.eindproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddGoalActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout layoutList;
    Button buttonAdd;
    Button buttonDoelToevoegen;

    ArrayList<Goal> GoalsList = new ArrayList<>();



    List<String> frequencyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        layoutList = findViewById(R.id.layout_list);
        buttonAdd = findViewById(R.id.button_add);
        buttonDoelToevoegen = findViewById(R.id.ButtonUpdateGoals);

        buttonAdd.setOnClickListener(this);
        buttonDoelToevoegen.setOnClickListener(this);

        frequencyList = Arrays.asList(getResources().getStringArray(R.array.Repeat_Goal));


    }





    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.button_add:

                addView();

                break;

            case R.id.ButtonUpdateGoals:


                if(checkIfValidAndRead()){

                    Intent intent = new Intent(AddGoalActivity.this, GoalsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("list", GoalsList);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }
                break;

        }

    }



    private void removeView(View view){

        layoutList.removeView(view);

    }

    private boolean checkIfValidAndRead() {
        GoalsList.clear();
        boolean result = true;

        for(int i=0;i<layoutList.getChildCount();i++){

            View goalView = layoutList.getChildAt(i);

            EditText editTextName = (EditText)goalView.findViewById(R.id.edit_goal_name);
            AppCompatSpinner spinnerTeam = (AppCompatSpinner)goalView.findViewById(R.id.spinner_team);

            Goal goal = new Goal();

            if(!editTextName.getText().toString().equals("")){
                goal.setGoalName(editTextName.getText().toString());
            }else {
                result = false;
                break;
            }

            if(spinnerTeam.getSelectedItemPosition()!=0){
                goal.setGoalFrequency(frequencyList.get(spinnerTeam.getSelectedItemPosition()));
            }else {
                result = false;
                break;
            }

            GoalsList.add(goal);

        }

        if(GoalsList.size()==0){
            result = false;
            Toast.makeText(this, "Voer doel in!", Toast.LENGTH_SHORT).show();
        }else if(!result){
            Toast.makeText(this, "Voer alle data correct", Toast.LENGTH_SHORT).show();
        }


        return result;
    }

    private void addView() {

        final View goalView = getLayoutInflater().inflate(R.layout.rij_add_goal,null,false);

        EditText editText = (EditText)goalView.findViewById(R.id.edit_goal_name);
        AppCompatSpinner spinnerTeam = (AppCompatSpinner)goalView.findViewById(R.id.spinner_team);
        ImageView imageClose = (ImageView)goalView.findViewById(R.id.image_remove);
        imageClose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                removeView(goalView);
            }
        });

        layoutList.addView(goalView);

    }
}