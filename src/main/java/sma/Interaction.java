package sma;

import sma.agents.Agent;

class Interaction {
    private final boolean[] agentsLibres;

    Interaction(int nbAgent) {
        agentsLibres = new boolean[nbAgent];
    }

    /**
     * ordonne à agent de se pousser
     *
     * @param agent
     */
    void libre(Agent agent) {
        agentsLibres[agent.id] = true;
    }

    /**
     * @param agent
     * @return vrai si agent libre
     */
    boolean suisJeLibre(Agent agent) {
        boolean estLibre = agentsLibres[agent.id];
        agentsLibres[agent.id] = false;
        return estLibre;
    }
}
