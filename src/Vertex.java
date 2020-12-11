public class Vertex {
    private char label;
    public boolean isVisited;
    public boolean isArticulation;
    public int ownedBy;

    public Vertex(char label){
        this.label=label;
        isArticulation=false;
        isVisited=false;
        ownedBy=-1;
    }

    public char getLabel() {
        return label;
    }

}
