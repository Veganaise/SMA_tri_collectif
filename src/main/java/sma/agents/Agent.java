package sma.agents;

import sma.ElementPhysique;
import sma.ObjetATrier;
import sma.Perception;
import sma.environnement.Case;
import sma.environnement.Environnement;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Agent extends ElementPhysique implements Runnable {
    public final int id;
    private final Environnement env;
    public final int t; // taille de la mémoire de l'agent

    public int rayonPerception; // rayon de perception de l'agent (= nb de casesVoisinage sur lesquelles il perçoit)

    int distanceDeDeplacement=1;
    double k_prise=0.1;
    double k_depose=0.1;
    ArrayList<Character> memoire = new ArrayList<>();

    public Perception percep;

    public Agent(Environnement env, int id, int rayonPerception, int t) {
        this.env = env;
        this.id = id;
        this.rayonPerception = rayonPerception;
        this.t = t;
        this.percep=new Perception(this);
    }


    private void perception() {
        env.percevoir(this);
    }

    private void action() {

        perception();

        // selection aléatoire d'un deplacement
        Random rdm = new Random();
        Integer direction = rdm.nextInt(4);

        env.deplacement(this, distanceDeDeplacement, direction);


        perception();

        // dépot d'objet
        Case caseActuelle = percep.caseActuelle;
        if(this.memoire.size() < this.t) {
            this.memoire.add(caseActuelle.getTypeObjet());
        } else {
            this.memoire.remove(0);
            this.memoire.add(caseActuelle.getTypeObjet());
        }
        System.out.println("Mémoire " + this.memoire);

        if(percep.porteUnObjet() && !caseActuelle.isOccupiedByObject()){
            ObjetATrier objetSurAgent = percep.objetSurAgent;
            double f = percep.getProportionDeObjet(objetSurAgent.getType());
            double proba=(f/(k_depose+f))*(f/(k_depose+f));
            if(rdm.nextDouble()<proba){
                System.out.println("agent "+id+" dépose objet "+objetSurAgent.getRepresentation());
                percep.objetSurAgent=null;
                env.deposeObjet(caseActuelle,objetSurAgent);
            } else {
                System.out.println("agent "+id+" porte toujours objet "+objetSurAgent.getRepresentation());
            }

        }else if(!percep.porteUnObjet() && caseActuelle.isOccupiedByObject()){//prise d'objet
            ObjetATrier objetSurCase = caseActuelle.objetSurCase;
            double f=percep.getProportionDeObjet(objetSurCase.type);
            double proba=(k_prise/((k_prise+f)))*(k_prise/(k_prise+f));
            if(rdm.nextDouble()<proba){
                System.out.println("agent "+id+" prend objet "+objetSurCase.getRepresentation());
                percep.objetSurAgent=objetSurCase;
                env.priseObjet(caseActuelle);
            }
        }
    }



    public void run() {
        int i = 0;
        while (i < 500) {
//            System.out.println("yo "+id);
            perception();
            action();
            i++;
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void iteration(){
        action();
    }

    public String getRepresentation(){
        return String.valueOf(id);
    }

}
