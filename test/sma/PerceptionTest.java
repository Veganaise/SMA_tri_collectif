package sma;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sma.agents.Agent;
import sma.agents.Memoire;
import sma.environnement.Case;
import sma.environnement.Environnement;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class PerceptionTest {
    Perception percep;

    Random rdm;

    @BeforeEach
    void setUp() {
        Environnement env=new Environnement(3,3);
        Agent agent=new Agent(env,0,1,1);
        percep=new Perception(agent);

        rdm = new Random();
    }

    @Test
    void getProportionDeObjet() {
        Case caseVoisineAvecObjetA=new Case(1,1);
        caseVoisineAvecObjetA.objetSurCase=new ObjetATrier('A');
        Case caseVoisineSansObjet= new Case(1,2);
        Case caseVoisineAvecObjetB = new Case(0,0);
        caseVoisineAvecObjetB.objetSurCase=new ObjetATrier('B');
        percep.casesVoisinage=new ArrayList<>();
        percep.casesVoisinage.add(caseVoisineAvecObjetA);
        percep.casesVoisinage.add(caseVoisineAvecObjetB);
        percep.casesVoisinage.add(caseVoisineSansObjet);

        System.out.println(percep.toString());

        assertEquals(percep.getProportionDeObjetDepot('A'),1.0/3.0);
        assertEquals(percep.getProportionDeObjetDepot('B'),1.0/3.0);
        assertEquals(percep.getProportionDeObjetDepot('C'),0.0);
    }


    @Test
    void getProportionDeObjetPrise() {
        Memoire memoire=new Memoire(2);
        assertEquals(0,percep.getProportionDeObjetPrise('A',memoire,rdm.nextDouble()));

        Case caseTest=new Case(1,1);
        memoire.miseAjour(caseTest);

        assertEquals(0,percep.getProportionDeObjetPrise('A',memoire,rdm.nextDouble()));

        caseTest.objetSurCase=new ObjetATrier('A');
        memoire.miseAjour(caseTest);
        System.out.println(memoire.toString());


        assertEquals(0.5,percep.getProportionDeObjetPrise('A',memoire,rdm.nextDouble()));
        assertEquals(0,percep.getProportionDeObjetPrise('B',memoire,0));

        assert (percep.getProportionDeObjetPrise('B',memoire,rdm.nextDouble())>0);
    }
}