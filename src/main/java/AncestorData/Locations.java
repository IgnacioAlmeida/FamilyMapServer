package AncestorData;


public class Locations {

    private Location[] data;


    public Locations(Location[] data) {
        this.data = data;
    }

    public Location locationAt(int random){
        return data[random];
    }


    public Location[] getLocations() {
        return data;
    }

    public void setLocations(Location[] data) {
        this.data = data;
    }
}
