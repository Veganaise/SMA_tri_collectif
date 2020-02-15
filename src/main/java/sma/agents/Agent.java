package sma.agents;

import sma.ElementPhysique;
import sma.ObjetATrier;
import sma.Perception;
import sma.environnement.Case;
import sma.environnement.Environnement;

import java.util.Random;

import static java.lang.Thread.sleep;

public class Agent extends ElementPhysique {
    public final int id;
    private final Environnement env;


    public int rayonPerception; // rayon de perception de l'agent (= nb de casesVoisinage sur lesquelles il perçoit)

    private int distanceDeDeplacement=1;
    double k_prise=0.1;
    double k_depose=0.3;
    private Memoire memoire;

    public Perception percep;

    public Agent(Environnement env, int id, int rayonPerception, int t) {
        this.env = env;
        this.id = id;
        this.rayonPerception = rayonPerception;
        this.memoire=new Memoire(t);
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
        Case caseActuelle = percep.caseActuelle;

        // mise à jour mémoire
        memoire.miseAjour(caseActuelle);
        System.out.println(memoire.toString());

        // dépot d'objet
        if(percep.porteUnObjet() && !caseActuelle.isOccupiedByObject()){
            ObjetATrier objetSurAgent = percep.objetSurAgent;
            double f = percep.getProportionDeObjetDepot(objetSurAgent.getType());
            double proba=(f/(k_depose+f))*(f/(k_depose+f));
            if(rdm.nextDouble()<proba){
                System.out.println("agent "+id+" dépose objet "+objetSurAgent.getRepresentation());
                percep.objetSurAgent=null;
                env.deposeObjet(caseActuelle,objetSurAgent);
            }

        } else if(!percep.porteUnObjet() && caseActuelle.isOccupiedByObject()){//prise d'objet
            ObjetATrier objetSurCase = caseActuelle.objetSurCase;
            double f=percep.getProportionDeObjetPrise(objetSurCase.getType(), memoire);
            double proba=(k_prise/((k_prise+f)))*(k_prise/(k_prise+f));
            if(rdm.nextDouble()<proba){
                System.out.println("agent "+id+" prend objet "+objetSurCase.getRepresentation());
                percep.objetSurAgent=objetSurCase;
                env.priseObjet(caseActuelle);
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
