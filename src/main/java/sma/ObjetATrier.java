package sma;

public class ObjetATrier extends ElementPhysique {
    public Integer type;
    public char id;

    public ObjetATrier(char id) {
        this.id = id;
        this.type= (int) id;
    }

    public String getRepresentation(){
        return String.valueOf(id);
    }

    public Integer getType(){
        return type;
    }

}
