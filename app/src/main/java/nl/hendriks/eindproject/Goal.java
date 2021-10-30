package nl.hendriks.eindproject;

import java.io.Serializable;

public class Goal implements Serializable {


    public String goalName;
    public String goalFrequency;

    public Goal(){

    }


    public Goal(String goalName, String goalFrequency){
        this.goalName = goalName;
        this.goalFrequency = goalFrequency;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public String getGoalFrequency() {
        return goalFrequency;
    }

    public void setGoalFrequency(String goalFrequency) {
        this.goalFrequency = goalFrequency;
    }
}
