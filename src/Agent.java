import static java.lang.Thread.sleep;

class Agent extends ElementPhysique implements Runnable {
    final int id;
    private final Environnement env;
    private final Interaction interaction;
    private final int rayonPerception;

    private Perception percep;


    Agent(Environnement env, Interaction interaction, int id, int rayonPerception) {
        this.env = env;
        this.interaction = interaction;
        this.id = id;
        this.rayonPerception = rayonPerception;
    }

    private void perception() {
        env.percevoir(this);
    }

    private void action() {
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
