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

    int tailleGrille=50;
    int nbObjetA=200;
    int nbObjetB=200;
    int nbIterations=1000;

    int nbAgents=30*2;

    public simpleExecution(String filePath) {
        batchLogger =new BatchLogger(filePath);
    }

    public void execute (String testName) throws Exception {
        batchLogger.writeLine(testName);

        batchLogger.writeLine(new String[]{"taille grille", String.valueOf(tailleGrille),"nb objet A", String.valueOf(nbObjetA),"nb objet B", String.valueOf(nbObjetB),"nb agents", String.valueOf(nbAgents)});
        batchLogger.writeLine(new String[]{"itération","nb moyen de voisins similaire","ration objet isolé"});
        ArrayList<String> line;


        Systeme systeme=new Systeme(nbAgents,nbObjetA,nbObjetB,tailleGrille,tailleGrille);

        for(int iteration =0;iteration<=nbIterations;++iteration){
            systeme.faireUneIteration();
            if(iteration%10==0){
                line=new ArrayList<>();
                line.add(String.valueOf(iteration));
                Pair<Double, Double> qualiteTri = systeme.mesurerTri();
                Double nombreMoyenDeVoisins=qualiteTri.getValue();
                line.add(String.valueOf(nombreMoyenDeVoisins));
                line.add(String.valueOf(qualiteTri.getKey()));
                batchLogger.writeLine(line);
            }
        }
    }
}
