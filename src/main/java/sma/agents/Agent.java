package sma.agents;

import sma.ElementPhysique;
import sma.Perception;
import sma.environnement.Environnement;

import java.util.Random;

import static java.lang.Thread.sleep;

public class Agent extends ElementPhysique implements Runnable {
    public final int id;
    private final Environnement env;
    public final int t; // taille de la mémoire de l'agent

    public int rayonPerception; // rayon de perception de l'agent (= nb de casesVoisinage sur lesquelles il perçoit)

    int distanceDeDeplacement=1;

    public Perception percep;

    public Agent(Environnement env, int id, int rayonPerception, int t) {
        this.env = env;
        this.id = id;
        this.rayonPerception = rayonPerception;
        this.t = t;
    }


    private void perception() {
        env.percevoir(this);
    }

    private void action() {
        //TODO: puis choix de pose/dépose d'objet

        // selection aléatoire d'un deplacement
        Random rdm = new Random();
        Integer direction = rdm.nextInt(4);

        env.deplacement(this, distanceDeDeplacement, direction);

        // prise/dépot d'objet
        if(percep.porteUnObjet() && ){
            //dépose possible d'objet
        }else{

        }

        /*boolean estBienPlace = blocDessousButId == null ||
                (percep.blocDessousCourant != null && (percep.blocDessousCourant.id == blocDessousButId));

        if (percep.estPousse || !estBienPlace) { //si il est poussé ou mal placé
            if (percep.blocDessus == null) {//au dessus de la pile (donc il peut bouger )
                // déplacement aléatoire
                boolean choisitPlace1 = Math.random() < 0.5;
                env.changerEmplacement(this, choisitPlace1 ? percep.place1 : percep.place2);
            } else {
                //propage poussée au bloc du dessus
                interaction.pousse(percep.blocDessus);
            }
        }*/
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

    public String getRepresentation(){
        return "Δ";
    }

}
