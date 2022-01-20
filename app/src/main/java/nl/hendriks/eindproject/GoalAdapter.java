package nl.hendriks.eindproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.GoalView> {

    ArrayList<Goal> GoalsList;
    private GoalView holder;

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
    public void onBindViewHolder(@NonNull GoalView holder, int position) {
        this.holder = holder;

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
        return GoalsList.get(position);
    }

    public Integer getPosition(View view) {
        int position = (int) view.getTag();
        return position;
    }


    public void setGoalChecked(int position) {
        GoalsList.get(position).setGoalComplete(true);
        addGoalActivity.saveData(GoalsList);
    }

    public class GoalView extends RecyclerView.ViewHolder implements View.OnClickListener {


        String convertedToString = String.valueOf(GoalsList);
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


        public void onClick(View view) {
            int position = (int) view.getTag();
            Toast.makeText(view.getContext(), "Doel: " + GoalsList.get(position).getGoalName() + " is gechecked.", Toast.LENGTH_SHORT).show();
            setGoalChecked(position);

        }


    }


}

