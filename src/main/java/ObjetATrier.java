public class ObjetATrier extends ElementPhysique {
    public char id;

    public ObjetATrier(char id) {
        this.id = id;
    }

    public String getRepresentation(){
        return String.valueOf(id);
    }

}
