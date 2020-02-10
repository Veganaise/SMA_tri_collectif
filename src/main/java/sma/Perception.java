package sma;

import sma.agents.Agent;

import java.util.ArrayList;

public class Perception {
    final Agent agent;
    public ArrayList cases;

    boolean estLibre;

    public Perception(Agent agent) {
        this.agent = agent;
    }

    public void setEstLibre(boolean estLibre) {
        this.estLibre = estLibre;
    }
}
