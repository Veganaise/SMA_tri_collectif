package executions;


import javafx.util.Pair;
import logger.BatchLogger;
import sma.Systeme;

import java.util.ArrayList;

public class simpleExecution {
    public static void main(String[] args){
        simpleExecution simpleExecution =new simpleExecution("./resultats/simple_execution.txt");
        try {
            simpleExecution.execute("nombre moyen d'objets similiaires proches selon le nombre d'agents et le nombre d'itérations");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    BatchLogger batchLogger;

    // nombre de test pris pour faire une moyenne
    private final int NB_MEAN=3;

    int m=20;
    int n=60;
    int nbObjetA=300;
    int nbObjetB=00;
    int nbIterations=100000;

    int nbAgents=64;

    public simpleExecution(String filePath) {
        batchLogger =new BatchLogger(filePath);
    }

    public void execute (String testName) throws Exception {
        batchLogger.writeLine(testName);

        batchLogger.writeLine(new String[]{"taille grille", m+"*"+n,"nb objet A", String.valueOf(nbObjetA),"nb objet B", String.valueOf(nbObjetB),"nb agents", String.valueOf(nbAgents)});
        batchLogger.writeLine(new String[]{"itération","nb moyen de voisins similaire","ration objet isolé"});
        ArrayList<String> line;


        Systeme systeme=new Systeme(nbAgents,nbObjetA,nbObjetB,m,n);

        for(int iteration =0;iteration<=nbIterations;++iteration){
            systeme.faireUneIteration();
            if(iteration%1000==0){
                System.out.println("iteration:" + iteration);
                line=new ArrayList<>();
                line.add(String.valueOf(iteration));
                Pair<Double, Double> qualiteTri = systeme.mesurerTri();
                Double nombreMoyenDeVoisins=qualiteTri.getValue();
                line.add(String.valueOf(nombreMoyenDeVoisins));
                Double ratioObjetsIsole = qualiteTri.getKey();
                line.add(String.valueOf(ratioObjetsIsole));
                batchLogger.writeLine(line);
                systeme.printGrille();

                System.out.println("nb moyen de voisin de même type:"+String.format( "%.2f", nombreMoyenDeVoisins ));
                System.out.println("ratio d'objets isolés:"+String.format( "%.2f", ratioObjetsIsole ));
                System.out.println("***************************");
            }
        }
    }
}
