package sma;

import sma.agents.Agent;
import sma.environnement.Environnement;

import java.util.ArrayList;

public class Systeme {
    private final ArrayList<Agent> agents;
    private final Environnement env;

    int nA=3;
    int nB=3;
    int rayonPerc=2;
    int t=10;

    public Systeme(int nbAgents, int nA, int nB) {
        env = new Environnement(6,6);
        agents = new ArrayList<Agent>(nbAgents);


        // instanciation des sma.agents
        for (int i = 0; i < nbAgents; ++i) {
            agents.add(new Agent(env,i,rayonPerc,t));
        }
        env.initializePositionOfElements(agents,nA,nB);
    }

    void startSystem() {
        for (Agent agent :
                agents) {
            Thread t = new Thread(agent);
            t.start();
        }
    }

    public void print(){
        env.print();
    }
}
