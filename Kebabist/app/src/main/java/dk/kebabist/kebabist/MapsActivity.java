package dk.kebabist.kebabist;

import android.content.pm.PackageManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import dk.kebabist.kebabist.network.KebabEntry;
import dk.kebabist.kebabist.network.KebabServiceCaller;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        KebabServiceCaller kebabServiceCaller = new KebabServiceCaller();
        try {
            List<KebabEntry> kebabPlaces = kebabServiceCaller.getService().listKebabPlaces().execute().body();
            for (KebabEntry kebabPlace : kebabPlaces) {
                System.out.println(kebabPlace.getName());
                Toast.makeText(getApplicationContext(), "Kebabplaces updated!", Toast.LENGTH_LONG).show();
            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            Toast.makeText(getApplicationContext(), "Please grant location permission", Toast.LENGTH_LONG).show();
        }

        LatLng id1 = new LatLng(56.157722, 10.206034);
        String id1name = "Ali Baba";
        mMap.addMarker(new MarkerOptions().position(id1).title(id1name));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(id1));
    }
}