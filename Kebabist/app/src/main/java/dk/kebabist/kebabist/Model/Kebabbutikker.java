package dk.kebabist.kebabist.Model;

import com.google.android.gms.maps.model.LatLng;

public class Kebabbutikker {

    private String ID;
    private String name;
    private LatLng coords;
    private String phone;

    public Kebabbutikker(String ID, String name, LatLng coords, String phone) {
        this.ID = ID;
        this.name = name;
        this.coords = coords;
        this.phone = phone;
    }
}
