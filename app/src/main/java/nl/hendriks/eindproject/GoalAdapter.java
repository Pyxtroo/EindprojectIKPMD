package nl.hendriks.eindproject;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.GoalView> {

    ArrayList<Goal> GoalsList;


    private SharedPreferences sharedPreferences;
    AddGoalActivity addGoalActivity;


    public GoalAdapter(ArrayList<Goal> GoalsList, Context context) {
        this.GoalsList = GoalsList;
        addGoalActivity = new AddGoalActivity(context);
    }




    @NonNull
    @Override
    public GoalView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_goal, parent, false);

        return new GoalView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoalView holder, int position) {  //pakt elk goal en zet in m in de goalstext
        Goal goal = GoalsList.get(position);
        holder.textGoalName.setText(goal.getGoalName());
        holder.textGoalFrequency.setText(goal.getGoalFrequency());
        holder.goalCard.setTag(position);


    }

    @Override
    public int getItemCount() {
        return GoalsList.size();

    }

    public Goal getItem(int position) {
        System.out.println("times");
        return GoalsList.get(position);
    }

    public Integer getPosition(View view) {
        int position = (int) view.getTag();
        System.out.println("positie is: " + position);
        return position;
    }



    public void setGoalChecked(int position) {
        System.out.println(GoalsList);
        GoalsList.get(position).setGoalComplete(true);
        addGoalActivity.saveData(GoalsList);
    }


    public class GoalView extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView textGoalName, textGoalFrequency;
        LinearLayout goalCard;
        public View view;
        CheckBox goalCheck;
        AddGoalActivity addGoalActivity;
        GoalAdapter goalAdapter;


        public GoalView(@NonNull View itemView) {
            super(itemView);
            textGoalName = (TextView) itemView.findViewById(R.id.text_goal_name);
            textGoalFrequency = (TextView) itemView.findViewById(R.id.text_goal_frequency);
            goalCard = (LinearLayout) itemView.findViewById(R.id.goalCard);
            goalCard.setOnClickListener(this);
            view = itemView;




        }


        @Override
        public void onClick(View view) {
            int position = (int) view.getTag();
            Toast.makeText(view.getContext(), "Doel: " + GoalsList.get(position).getGoalName() + " is gechecked.", Toast.LENGTH_SHORT).show();
            setGoalChecked(position);

        }


    }


}

