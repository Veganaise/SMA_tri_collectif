package sma.environnement;

import sma.ElementPhysique;
import sma.ObjetATrier;
import sma.agents.Agent;

import java.util.ArrayList;
import java.util.Arrays;

public class Grille {
    private int n,m;

    public ArrayList<Case> getCases() {
        ArrayList<Case> cases = new ArrayList<Case>(m*n);
        for(int x=0;x<m;++x){
            cases.addAll(Arrays.asList(this.cases[x]).subList(0, n));
        }
        return cases;
    }

    private Case[][] cases;

    public Grille(int n, int m) {
        this.n = n;
        this.m = m;
        cases=new Case[m][n];
        for(int x=0;x<m;++x){
            for(int y=0;y<n;++y){
                cases[x][y]=new Case(x+1,y+1);
            }
        }
    }

    public ArrayList<Case> getVoisinage(int xAgent, int yAgent, int rayonPerception) {
        ArrayList<Case> neighbors = new ArrayList<>();

        for (int x = xAgent-rayonPerception; x <= xAgent+rayonPerception; x++) {
            neighbors.add(getCase(x, yAgent));
        }

        for (int y = yAgent-rayonPerception; y <= yAgent+rayonPerception; y++) {
            if(!neighbors.contains(getCase(xAgent, y))) {
                neighbors.add(getCase(xAgent, y));
            }
        }

        return neighbors;
    }

    /**
     * return Case at specified position (with management of overflow).
     * @param x
     * @param y
     * @return Case
     */
    public Case getCase(int x, int y){
        if(x<(-m) || y<(-n)) {
            System.out.println("erreur dans getCase de Grille. argument trop négatif");
            return null;
        }

        x=x<=0? m+x:x;
        y=y<=0? n+y:y;


        return cases[(x-1)%m][(y-1)%n];
    }


    public void print(){
        StringBuilder objetsString=new StringBuilder();
        StringBuilder agentString=new StringBuilder();

        //TODO: gestion des agents
        objetsString.append("Objets:\n");
        agentString.append("Agents: \n");
        for(int x=0;x<m;++x){
            for(int y=0;y<n;++y){
                ObjetATrier elmt = cases[x][y].objetSurCase;
                if (elmt == null) {
                    objetsString.append("_");
                } else {
                    objetsString.append(elmt.getRepresentation());
                }


                Agent agent=cases[x][y].agentSurCase;
                if(agent==null){
                    objetsString.append("_");
                }else{
                    objetsString.append(agent.getRepresentation());
                }

                objetsString.append(" ");
            }
            objetsString.append("\n");
        }
        System.out.println(objetsString.toString());

        System.out.println();
    }

    // stats
    public double countNumberOfSameTypeObjectAround(Case caseOfObject) throws Exception {
        ObjetATrier obj=caseOfObject.objetSurCase;
        if(obj==null){
            throw new Exception("objet null");
        }
        ArrayList<Case> voisinage = getVoisinage(caseOfObject.x, caseOfObject.y, 1);
        voisinage.remove(caseOfObject);
        int compteurObjetsMemeType=0;
        for(Case caseVoisine: voisinage){
            if(caseVoisine.isOccupiedByObject()&& caseVoisine.objetSurCase.shareSameType(obj)){
                compteurObjetsMemeType++;
            }
        }
        return compteurObjetsMemeType;
    }


}
