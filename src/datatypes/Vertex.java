package datatypes;

/**
 * Created by Marcel on 23.10.2016.
 */
public class Vertex implements Comparable<Vertex> {

    private String name;
    private int attributeValue;
    private boolean attributed;
    public Vertex predecessor;

    public Vertex(String name, int attributeValue){
        this.name = name;
        this.attributeValue = attributeValue;
        this.attributed = true;
    }

    public Vertex(String name){
        this.name = name;
        attributed = false;
    }

    public String getName() {
        return name;
    }

    public int getAttributeValue() {
        return attributeValue;
    }

    public boolean isAttributed() {
        return attributed;
    }

    public Vertex getPredecessor() {
        return predecessor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttributeValue(int attributeValue) {
        this.attributeValue = attributeValue;
    }

    public void setAttributed(boolean attributed) {
        this.attributed = attributed;
    }

    public void setPredecessor(Vertex predecessor) {
        this.predecessor = predecessor;
    }


    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Vertex)) return false;
        return ((Vertex) o).getName().equals(name);
    }

    @Override
    public int hashCode(){
        return name.hashCode();
    }

    @Override
    public int compareTo(Vertex o) {
        return Double.compare(attributeValue, o.attributeValue);
    }
}
