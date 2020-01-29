import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;


class Environnement {
    private Grille grille;

    public Environnement(int m, int n) {
        this.grille=new Grille(n,m);
    }


    /**
     *  place les agents et instancie les objets à trier, chacun sur des cases differentes.
     * @param agents agents à placer
     * @param nA nombre d'objet A
     * @param nB nombre d'objet B
     */
    void initializePositionOfElements(List<Agent> agents, int nA, int nB) {

    }


    synchronized void percevoir(Agent agent) {
        Perception perception = agent.percep;

        // Voisinage
        perception.cases = grille.getVoisinage(agent.x, agent.y, agent.rayonPerception);
    }



    synchronized private void printEnvironnement() {
//        System.out.println("*********************");
//        for (int i = 0; i < colonnes.size(); ++i) {
//            Stack<Agent> colonne = colonnes.get(i);
//            System.out.print("colonne " + (i + 1) + ": {");
//            for (Agent agentAtJ : colonne) {
//                System.out.print(agentAtJ.id + " ");
//            }
//            System.out.print("}\n");
//        }
//        System.out.println();
//        System.out.println();
    }

}
