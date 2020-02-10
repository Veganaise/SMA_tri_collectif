package sma.environnement;

import sma.ObjetATrier;
import sma.Perception;
import sma.agents.Agent;

import java.util.*;

public class Environnement {
    private Grille grille;
    private HashMap<Integer, Case> positionDesAgents; // key: id of agent ; value: case (position) of agent

    public Environnement(int m, int n) {
        this.grille=new Grille(n,m);
        this.positionDesAgents =new HashMap<>();
    }


    /**
     *  place les sma.agents et instancie les objets à trier, chacun sur des cases differentes.
     * @param agents sma.agents à placer
     * @param nA nombre d'objet A
     * @param nB nombre d'objet B
     */
    public void initializePositionOfElements(List<Agent> agents, int nA, int nB) {
        ArrayList<Case> casesDisponibles=grille.getCases();
        Random random=new Random();
        //TODO: changez gestion cases disponibles
        for(Agent agent:agents){
            Case rdmCase=casesDisponibles.get(random.nextInt(casesDisponibles.size()));
            rdmCase.agentSurCase =agent;
            positionDesAgents.put(agent.id,rdmCase);
            casesDisponibles.remove(rdmCase);
        }

        for(int i=0;i<nA;++i){
            Case rdmCase=casesDisponibles.get(random.nextInt(casesDisponibles.size()));
            rdmCase.objetSurCase =new ObjetATrier('A');
            casesDisponibles.remove(rdmCase);
        }
        for(int i=0;i<nB;++i){
            Case rdmCase=casesDisponibles.get(random.nextInt(casesDisponibles.size()));
            rdmCase.objetSurCase =new ObjetATrier('B');
            casesDisponibles.remove(rdmCase);
        }


    }

    synchronized public void percevoir(Agent agent) {
        Perception perception = agent.percep;
        Case position=positionDesAgents.get(agent.id);
        assert(position.agentSurCase.getClass()==Agent.class
                && position.agentSurCase ==agent);


        // Voisinage
        perception.cases = grille.getVoisinage(position.x, position.y, agent.rayonPerception);
    }




    synchronized public void print() {
        grille.print();
    }

}
