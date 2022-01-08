package nl.hendriks.eindproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.GoalView> {



    private ArrayList<Goal> GoalsList;



    public GoalAdapter(ArrayList<Goal> GoalsList) {
        this.GoalsList = GoalsList;

    }

    @NonNull
    @Override
    public GoalView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_goal,parent,false);

        return new GoalView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoalView holder, int position) {  //pakt elk goal en zet in m in de goalstext
        Goal goal = GoalsList.get(position);
        holder.textGoalName.setText(goal.getGoalName());
        holder.textGoalFrequency.setText(goal.getGoalFrequency());


    }

    @Override
    public int getItemCount() {
        System.out.println("list is: " + GoalsList);
        return GoalsList.size();

    }

    public class GoalView extends RecyclerView.ViewHolder{
        String convertedToString = String.valueOf(GoalsList);
        TextView textGoalName, textGoalFrequency;
        public GoalView(@NonNull View itemView) {
            super(itemView);
            String[] dest = new String[GoalsList.size()];
            System.out.println("list is en: " + GoalsList.get(0).toString());

            textGoalName = (TextView)itemView.findViewById(R.id.text_goal_name);
            textGoalFrequency = (TextView)itemView.findViewById(R.id.text_goal_frequency);

        }
    }

}