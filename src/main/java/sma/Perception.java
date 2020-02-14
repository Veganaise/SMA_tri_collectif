package sma;

import sma.agents.Agent;
import sma.environnement.Case;

import java.util.ArrayList;

public class Perception {
    final Agent agent;
    public ArrayList<Case> casesVoisinage;

    public ObjetATrier objetSurAgent;

    public Case caseActuelle;

    public Perception(Agent agent) {
        this.agent = agent;
    }

    public boolean porteUnObjet(){return objetSurAgent!=null;}

    public double getProportionDeObjet(int type){
        int compteur=0;
        for (Case c :
        casesVoisinage){
            if(c.getTypeObjet()==type){
                compteur++;
            }
        }
        return (double)compteur/casesVoisinage.size();
    }
}
