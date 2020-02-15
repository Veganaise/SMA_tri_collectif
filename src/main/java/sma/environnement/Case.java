package sma.environnement;

import sma.ObjetATrier;
import sma.agents.Agent;

public class Case {
    public Integer x,y;

    public ObjetATrier objetSurCase;

    public Agent agentSurCase;



    public Case(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public boolean isOccupiedByAgent(){
        return agentSurCase!=null;
    }

    public boolean isOccupiedByObject(){
        return objetSurCase!=null;
    }

    public char getTypeObjet(){
        if(objetSurCase==null){
            return ObjetATrier.NON_EXISTANT;
        }else{
            return objetSurCase.id;
        }
    }

    public String toString(){
        return "("+x+","+y+")";
    }

}
