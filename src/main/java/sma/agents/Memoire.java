package sma.agents;

import sma.environnement.Case;

import java.util.ArrayList;

public class Memoire {

    private ArrayList<Character> memoire = new ArrayList<>();

    private static final int DEFAULT_T = 10;
    public final int t; // taille de la mémoire de l'agent

    public Memoire(int t) {
        this.t = t;
    }

    public Memoire() {
        this(DEFAULT_T);
    }


    public void miseAjour(Case caseActuelle) {
        if(this.memoire.size() < this.t) {
            this.memoire.add(caseActuelle.getTypeObjet());
        } else {
            this.memoire.remove(0);
            this.memoire.add(caseActuelle.getTypeObjet());
        }
    }

    public ArrayList<Character> getEtat() {
        return memoire;
    }

    @Override
    public String toString() {
        return "Memoire{" + memoire +
                '}';
    }
}