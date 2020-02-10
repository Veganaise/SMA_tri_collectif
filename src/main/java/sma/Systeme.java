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

    Integer maxIterations=100;

    public Systeme(int nbAgents, int nA, int nB,int m,int n) {
        env = new Environnement(m,n);
        agents = new ArrayList<Agent>(nbAgents);


        // instanciation des sma.agents
        for (int i = 0; i < nbAgents; ++i) {
            agents.add(new Agent(env,i,rayonPerc,t));
        }
        env.initializePositionOfElements(agents,nA,nB);
    }

    public Systeme(int nbAgents, int nA, int nB){
        this(nbAgents,nA,nB,6,6);
    }

    public void startSystem() {
//        for (Agent agent :
//                agents) {
//            Thread t = new Thread(agent);
//            t.start();
//        }

        int token=0;
        for(int i=1;i<=maxIterations;++i){
            System.out.println("iteration "+i);
            agents.get(token).iteration();
            token=(token + 1)%agents.size();


            print();
        }

    }

    public void print(){
        env.print();
    }
}
