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

    public double getProportionDeObjetDepot(int type){
        int compteur=0;
        for (Case c : casesVoisinage){
            if(c.getTypeObjet()==type){
                compteur++;
            }
        }
        return (double)compteur/casesVoisinage.size();
    }

    public double getProportionDeObjetPrise(int type, ArrayList<Character> memoire) {
        int compteurType = 0;
        int compteurNonType = 0;
        double e = 0.05;
        for (Character typeObjet : memoire) {
            if(typeObjet == type) {
                compteurType++;
            } else if (typeObjet != 'O') {
                compteurNonType++;
            }
        }
        return (compteurType+compteurNonType*e)/this.agent.t;
    }
}
