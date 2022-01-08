package nl.hendriks.eindproject;

import java.io.Serializable;

public class Goal implements Serializable {


    public String goalName;
    public String goalFrequency;
    public Boolean goalComplete;

    public Goal(){

    }


    public Goal(String goalName, String goalFrequency, Boolean goalComplete){
        this.goalName = goalName;
        this.goalFrequency = goalFrequency;
        this.goalComplete = goalComplete;
    }

    public Boolean getGoalComplete() {
        return goalComplete;
    }

    public void setGoalComplete(Boolean goalComplete) {
        this.goalComplete = goalComplete;
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
