package AncestorData;

public class Names {
    private String[] data;

    public String nameAt(int random){
        return data[random];
    }
    public Names(String[] data) {
        this.data = data;
    }

    public String[] getNames() {
        return data;
    }

    public void setNames(String[] data) {
        this.data = data;
    }
}
