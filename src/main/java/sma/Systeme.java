package sma;

import javafx.util.Pair;
import sma.agents.Agent;
import sma.environnement.Environnement;

import java.util.ArrayList;

public class Systeme {
    private final ArrayList<Agent> agents;
    public final Environnement env;

    int nA=3;
    int nB=3;
    int rayonPerc=1;
    int t=10;

    Integer maxIterations=1000;

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

        int token=0;
        for(int i=1;i<=maxIterations;++i){
            System.out.println("iteration "+i);

            for(Agent agent: agents){
                agent.iteration();
                print();
            }
//            print();
        }

    }

    public void print(){
        env.print();
    }

    public void printGrille(){
        System.out.println("état grille:");
        String etatGrille = env.printGrille();
        System.out.println(etatGrille);
    }

    /**
     * une itération = chaque agent fait une action (perception + mouvement + prise/depose)
     */
    public void faireUneIteration() {
        for(Agent agent: agents){
            agent.iteration();
        }
    }

    /**
     * mesure la qualité du tri avec le nombre d'objets isolés et le nombre moyen d'objets de même type dans le voisinage
     * des objets
     * @return < ration nombre objet isolé sur nombre total d'objet, nombre moyen d'objets de même type dans le voisinage>
     */
    public Pair<Double,Double> mesurerTri() {
        return env.mesurerTri();
    }
}
