package sma;

public class ObjetATrier extends ElementPhysique {
    public static final char NON_EXISTANT = 'O';
    public Integer type;
    public char id;

    public ObjetATrier(char id) {
        this.id = id;
        this.type= (int) id;
        if(type==NON_EXISTANT){
            new Exception("Impossible d'ajouter un objet de ce type").printStackTrace();
        }
    }

    public String getRepresentation(){
        return String.valueOf(id);
    }

    public Integer getType(){
        return type;
    }

    public boolean shareSameType(ObjetATrier objet){
        return this.id==objet.id;
    }
}
