package lars.kebabist;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        i = getIntent();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        try {
            JSONObject json = new JSONObject(i.getStringExtra("json"));
            JSONArray kebabSteder = json.getJSONArray("Kebabsteder");

            for(int i = 0; i < kebabSteder.length(); i++) {
                JSONObject temp = kebabSteder.getJSONObject(i);
                LatLng id1 = new LatLng(Double.valueOf(temp.get("Latitude").toString()), Double.valueOf(temp.get("Longitude").toString()));
                String id1name = temp.getString("Name");
                mMap.addMarker(new MarkerOptions().position(id1).title(id1name));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
