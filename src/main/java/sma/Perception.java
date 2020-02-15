package sma;

import sma.agents.Agent;
import sma.agents.Memoire;
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

    public double getProportionDeObjetPrise(int type, Memoire memoire, double e) {
        int compteurType = 0;
        int compteurNonType = 0;
        for (Character typeObjet : memoire.getEtat()) {
            if(typeObjet == type) {
                compteurType++;
            } else if (typeObjet != ObjetATrier.NON_EXISTANT) {
                compteurNonType++;
            }
        }
        return (compteurType+compteurNonType*e)/memoire.t;
    }

    private static final double DEFAULT_ERROR = 0.05;
    public double getProportionDeObjetPrise(int type, Memoire memoire){
        return getProportionDeObjetPrise(type,memoire,DEFAULT_ERROR);
    }


    @Override
    public String toString() {
        return "Perception{" +
                "agent=" + agent.id +
                ", casesVoisinage=" + casesVoisinage +
                ", objetSurAgent=" + objetSurAgent +
                ", caseActuelle=" + caseActuelle +
                '}';
    }
}
