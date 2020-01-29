import static java.lang.Thread.sleep;

class Agent extends ElementPhysique implements Runnable {
    final int id;
    private final Environnement env;
    private final int t; // taille de la mémoire de l'agent

    public int rayonPerception; // rayon de perception de l'agent (= nb de cases sur lesquelles il perçoit)

    // Position de l'agent sur la grille
    public int x;
    public int y;


    public Perception percep;


    Agent(Environnement env,  int id, int rayonPerception, int t, int x, int y) {
        this.env = env;
        this.id = id;
        this.rayonPerception = rayonPerception;
        this.t = t;
        this.x = x;
        this.y = y;
    }

    private void perception() {
        env.percevoir(this);
    }

    private void action() {
        //TODO: toujours déplacement
        //TODO: puis choix de pose/dépose d'objet

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


    @Override
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

}
