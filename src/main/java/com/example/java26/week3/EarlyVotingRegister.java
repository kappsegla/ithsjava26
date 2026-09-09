package com.example.java26.week3;

public class EarlyVotingRegister {

    static void main() {

        IO.println("Voting registration v1.0");

        Vote[] votes = new Vote[10];

        int i = 0;
        while (i < votes.length) {
            String name = IO.readln("Voter name:");
            int eNr = Integer.parseInt(IO.readln("Envelope number:"));
            //Check if eNr is different from previously used numbers
            if (isEnvelopeNumberUnique(eNr, votes)) {
                Vote vote = new Vote(name, eNr);
                votes[i] = vote;
                i++;
            }
            else {
                IO.println("Invalid envelope number");
            }
        }
    }

    static boolean isEnvelopeNumberUnique(int envelopeNumber, Vote[] votes) {
        for (int i = 0; i < votes.length; i++) {
            if (votes[i] != null && envelopeNumber == votes[i].eNumber()) //Must check for null to avoid NullPointerException
                return false;
        }
        return true;
    }
}

record Vote(String name, int eNumber) {
}
