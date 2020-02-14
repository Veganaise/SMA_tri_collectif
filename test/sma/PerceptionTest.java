package sma;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sma.agents.Agent;
import sma.environnement.Case;
import sma.environnement.Environnement;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PerceptionTest {
    Perception percep;

    @BeforeEach
    void setUp() {
        Environnement env=new Environnement(3,3);
        Agent agent=new Agent(env,0,1,1);
        percep=new Perception(agent);
        Case caseVoisineAvecObjetA=new Case(1,1);
        caseVoisineAvecObjetA.objetSurCase=new ObjetATrier('A');
        Case caseVoisineSansObjet= new Case(1,2);
        Case caseVoisineAvecObjetB = new Case(0,0);
        caseVoisineAvecObjetB.objetSurCase=new ObjetATrier('B');
        percep.casesVoisinage=new ArrayList<>();
        percep.casesVoisinage.add(caseVoisineAvecObjetA);
        percep.casesVoisinage.add(caseVoisineAvecObjetB);
        percep.casesVoisinage.add(caseVoisineSansObjet);

    }

    @Test
    void getProportionDeObjet() {
        assertEquals(percep.getProportionDeObjet('A'),1.0/3.0);
        assertEquals(percep.getProportionDeObjet('B'),1.0/3.0);
        assertEquals(percep.getProportionDeObjet('C'),0.0);


    }
}