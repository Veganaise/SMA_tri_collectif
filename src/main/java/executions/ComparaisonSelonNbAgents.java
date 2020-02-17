package executions;


import javafx.util.Pair;
import logger.BatchLogger;
import sma.Systeme;

import java.util.ArrayList;

public class ComparaisonSelonNbAgents {
    public static void main(String[] args){
        ComparaisonSelonNbAgents batchComparaisonAlgorithmes =new ComparaisonSelonNbAgents("./resultats/parametreNbAgents.txt");
        try {
            batchComparaisonAlgorithmes.execute("nombre moyen d'objets similiaires proches selon le nombre d'agents et le nombre d'itérations");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    BatchLogger batchLogger;

    // nombre de test pris pour faire une moyenne
    private final int NB_MEAN=3;

    int tailleGrille=6;
    int nbObjetA=10;
    int nbObjetB=10;
    int nbIterations=1000;

    int nbAgents=16;

    public ComparaisonSelonNbAgents(String filePath) {
        batchLogger =new BatchLogger(filePath);
    }

    public void execute (String testName) throws Exception {
        batchLogger.writeLine(testName);

        batchLogger.writeLine(new String[]{"nb agent","itérations"});
        batchLogger.writeLine(new String[]{"","0","100","200","300","400","500","600","700","800","900","1000"});
        ArrayList<String> line;

        long startTime,endTime,duration;

        for(int nbAgents=1;nbAgents<=32;nbAgents*=2){

            Systeme systeme=new Systeme(nbAgents,nbObjetA,nbObjetB,tailleGrille,tailleGrille);
            line=new ArrayList<>();
            line.add(String.valueOf(nbAgents));

            for(int iteration =0;iteration<=nbIterations;++iteration){
                systeme.faireUneIteration();
                if(iteration%100==0){
                    Pair<Double, Double> qualiteTri = systeme.mesurerTri();
                    Double nombreMoyenDeVoisins=qualiteTri.getValue();
                    line.add(String.valueOf(nombreMoyenDeVoisins));
                }
            }

            batchLogger.writeLine(line);

        }
    }

}
