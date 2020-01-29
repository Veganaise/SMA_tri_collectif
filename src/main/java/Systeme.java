import java.util.ArrayList;
import java.util.Collections;

class Systeme {
    private final ArrayList<Agent> agents;
    private final Environnement env;

    int nA=3;
    int nB=3;


    Systeme(int nbAgents) {
        env = new Environnement(6,6);
        agents = new ArrayList<>(nbAgents);


        // instanciation des agents
        for (int i = 0; i < nbAgents; ++i) {
            agents.add(new Agent());
        }
        Collections.shuffle(agents);
        env.initializePositionOfElements(agents);
    }

    void startSystem() {
        for (Agent agent :
                agents) {
            Thread t = new Thread(agent);
            t.start();
        }
    }
}
