package com.example.java26.exercises.week4;

import java.util.Arrays;

public class Team {
    private String teamName;
    private Employee teamLeader;

    public Team(String teamName, Employee teamLeader) {
        this.teamName = teamName;
        this.teamLeader = teamLeader;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Employee getTeamLeader() {
        return teamLeader;
    }

    public void copyTo(Team clone) {
        clone.teamName = teamName;
        //clone.teamLeader = teamLeader;
        clone.teamLeader = new Employee(teamLeader);
    }

    static void main() {
        Team team1 = new Team("Team 1",
                new Employee("Martin", 40000, "Education"));
        Team team2 = new Team("", null);
        team1.copyTo(team2);
        //Alt1 using temporary variable
        var teamLeader = team2.getTeamLeader();
        teamLeader.setSalary(50000);
        //Alt2 chaining method calls
        team2.getTeamLeader().setSalary(50000);

        IO.println(team1.teamName + " " + team1.teamLeader.getSalary());
        IO.println(team2.teamName + " " + team2.teamLeader.getSalary());
    }
}
