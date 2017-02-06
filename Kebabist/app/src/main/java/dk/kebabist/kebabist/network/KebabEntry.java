package dk.kebabist.kebabist.network;

import com.google.gson.annotations.SerializedName;

/**
 * Created by LeFanous on 06-02-2017.
 */

public class KebabEntry {
    @SerializedName("ID")
    private String ID;
    @SerializedName("Name")
    private String Name;
    @SerializedName("Latitude")
    private String Latitude;
    @SerializedName("Longitude")
    private String Longitude;
    @SerializedName("Phonenumber")
    private String Phonenumber;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getPhonenumber() {
        return Phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        Phonenumber = phonenumber;
    }
}
