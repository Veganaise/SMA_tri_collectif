public class Case {
    public Integer x,y;
    private ElementPhysique elementSurCase;

    public Case(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }


    public ElementPhysique getElement() {
        return elementSurCase;
    }

    public void setElement(ElementPhysique elementSurCase) {
        this.elementSurCase = elementSurCase;
    }
}
