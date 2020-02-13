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
     *  place les sma.agents et instancie les objets à trier, chacun sur des casesVoisinage differentes.
     * @param agents sma.agents à placer
     * @param nA nombre d'objet A
     * @param nB nombre d'objet B
     */
    public void initializePositionOfElements(List<Agent> agents, int nA, int nB) {
        ArrayList<Case> casesDisponibles=grille.getCases();
        Random random=new Random();
        //TODO: changez gestion casesVoisinage disponibles
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

        perception.caseActuelle=position;

        // Voisinage
        perception.casesVoisinage = grille.getVoisinage(position.x, position.y, agent.rayonPerception);
    }




    synchronized public void print() {
        grille.print();
    }

    private String[] directionsStrings={"","","",""};
    public void deplacement(Agent agent, int distanceDeDeplacement, Integer direction) {
        Case currentCase=positionDesAgents.get(agent.id);
        Integer x_objectif=currentCase.x;
        Integer y_objectif=currentCase.y;
        switch (direction){
            case 0:
                x_objectif+=distanceDeDeplacement;
                break;
            case 1:
                x_objectif-=distanceDeDeplacement;
                break;
            case 2:
                y_objectif+=distanceDeDeplacement;
                break;
            case 3:
                y_objectif-=distanceDeDeplacement;
                break;
        }

        Case caseObjectif=grille.getCase(x_objectif,y_objectif);
        // we move the agent only if the case is not occupied
        if(!caseObjectif.isOccupiedByAgent()) {
            currentCase.agentSurCase=null;
            caseObjectif.agentSurCase=agent;
            positionDesAgents.put(agent.id,caseObjectif);

            System.out.println("agent "+agent.id+" se déplace en "+caseObjectif.toString());
        }else{
            System.out.println("agent "+agent.id+" a essayé de se déplacer en  "+caseObjectif.toString());
        }

    }

    public void deposeObjet( Case caseDeDepose, ObjetATrier objetSurAgent)  {
        if(caseDeDepose.isOccupiedByObject()){
            new Exception("Dépose sur case déjà occupée").printStackTrace();
        }
        caseDeDepose.objetSurCase=objetSurAgent;
    }

    public void priseObjet(Case caseActuelle) {
        caseActuelle.objetSurCase=null;
    }
}
