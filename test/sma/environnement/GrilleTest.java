package sma.environnement;

import javafx.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sma.ObjetATrier;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GrilleTest {
    Grille grille;

    @BeforeEach
    void setUp() {
        grille=new Grille(3,3);
    }

    @Test
    void getVoisinage() {
        ArrayList<Case> voisinage = grille.getVoisinage(3, 3, 1);

        assert (voisinage.contains(grille.getCase(3,3)));
        assert (voisinage.contains(grille.getCase(2,3)));
        assert (voisinage.contains(grille.getCase(4,3)));
        assert (voisinage.contains(grille.getCase(3,2)));
        assert (voisinage.contains(grille.getCase(3,4)));

        assert (! voisinage.contains(grille.getCase(4,4)));
        assert (! voisinage.contains(grille.getCase(1,1)));
        assert (! voisinage.contains(grille.getCase(2,2)));
    }

    @Test
    @DisplayName("Je suis ton ami :)")
    void getCase() {
        Case caseBase=grille.getCase(1,1);
        assertEquals(caseBase.x,1);
        assertEquals(caseBase.y,1);

        Case caseModulo=grille.getCase(4,4);
        assertEquals(caseModulo.x,1);
        assertEquals(caseModulo.y,1);

        Case caseNegative=grille.getCase(0,1);
        assertEquals(3,caseNegative.x);
        assertEquals(1,caseNegative.y);
    }


    @Test
    void countNumberOfSameTypeObjectAround() {
        grille.getCase(2,2).objetSurCase=new ObjetATrier('A');
        grille.getCase(1,2).objetSurCase=new ObjetATrier('A');
        grille.getCase(3,2).objetSurCase=new ObjetATrier('B');

        try {
            assertEquals (1,grille.countNumberOfSameTypeObjectAround(grille.getCase(2,2)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // tests de mesure de qualité du tri

        Pair<Double, Double> mesure = grille.mesureDeQualiteDuTri();
        Double ratioIsole=mesure.getKey();
        Double moyenneObjetsVoisins=mesure.getValue();

        assertEquals(1.0/3.0,ratioIsole);
        // le calcul de moyenne que j'utilise provoque une légère erreur d'arrondis
        assert(Math.abs(moyenneObjetsVoisins- 2.0/3) <=0.0001);


        grille.getCase(3,1).objetSurCase=new ObjetATrier('B');

        // oui, c'est pas fou comme notation 😐
        Pair<Double, Double> mesure2 = grille.mesureDeQualiteDuTri();
        Double ratioIsole2=mesure2.getKey();
        Double moyenneObjetsVoisins2=mesure2.getValue();
        assertEquals(0,ratioIsole2);
        // le calcul de moyenne que j'utilise provoque une légère erreur d'arrondis
        assert(Math.abs(moyenneObjetsVoisins2- 1) <=0.0001);

    }

}