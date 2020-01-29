import java.util.ArrayList;
import java.util.Arrays;

public class Grille {
    private int n,m;

    public ArrayList<Case> getCases() {
        ArrayList<Case> cases = new ArrayList<>(m*n);
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

    public ArrayList getVoisinage(int x, int y, int rayonPerception) {
        ArrayList<Case> neighbors = new ArrayList<>();

        for (int xx = -rayonPerception; xx <= rayonPerception; xx++) {
            for (int yy = -rayonPerception; yy <= rayonPerception; yy++) {
                int xModulo = x + xx % m;
                int yModulo = y + yy % n;
                neighbors.add(new Case(xModulo, yModulo));
            }
        }
        return neighbors;
    }




}
