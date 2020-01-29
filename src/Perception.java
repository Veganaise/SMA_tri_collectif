import java.util.ArrayList;
import java.util.List;

class Perception {
    final Agent agent;
    public ArrayList cases;

    boolean estLibre;

    public Perception(Agent agent) {
        this.agent = agent;
    }

    public void setEstLibre(boolean estLibre) {
        this.estLibre = estLibre;
    }
}
