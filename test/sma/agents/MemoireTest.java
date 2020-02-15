package sma.agents;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sma.ObjetATrier;
import sma.environnement.Case;

import static org.junit.jupiter.api.Assertions.*;

class MemoireTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void miseAjour() {
        Memoire memoire=new Memoire(2);
        Case caseTest=new Case(1,1);
        memoire.miseAjour(caseTest);
        System.out.println(memoire.toString());
        assert (memoire.getEtat().contains(ObjetATrier.NON_EXISTANT));

        caseTest.objetSurCase=new ObjetATrier('a');
        memoire.miseAjour(caseTest);
        System.out.println(memoire.toString());
        assert (memoire.getEtat().contains(ObjetATrier.NON_EXISTANT));
        assert (memoire.getEtat().contains('a'));

        caseTest.objetSurCase=new ObjetATrier('b');
        memoire.miseAjour(caseTest);
        System.out.println(memoire.toString());
        assert ( ! memoire.getEtat().contains(ObjetATrier.NON_EXISTANT));
        assert (memoire.getEtat().contains('a'));
        assert (memoire.getEtat().contains('b'));
    }
}