package sma;

import sma.agents.Agent;
import sma.environnement.Case;

import java.util.ArrayList;

public class Perception {
    final Agent agent;
    public ArrayList<Case> casesVoisinage;

    public ObjetATrier objetSurAgent;

    public Perception(Agent agent) {
        this.agent = agent;
    }

    public boolean porteUnObjet(){return objetSurAgent!=null;}
}
