package dk.kebabist.kebabist;

import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dk.kebabist.kebabist.Model.Kebabbutikker;
import dk.kebabist.kebabist.Service.DatabaseService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        getShopDetails();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true); //This line ignores location permission

        LatLng id1 = new LatLng(56.157722, 10.206034);
        String id1name = "Ali Baba";
        mMap.addMarker(new MarkerOptions().position(id1).title(id1name));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(id1));
    }

    private void getShopDetails() {
        showpDialog();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ayg.dk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DatabaseService service = retrofit.create(DatabaseService.class);
        Call<List<Kebabbutikker>> call = service.getShopDetails();

        call.enqueue(new Callback<List<Kebabbutikker>>() {
            @Override
            public void onResponse(Response<List<Kebabbutikker>> response, Retrofit retrofit) {
                List<Kebabbutikker> kebabbutikkers = response.body();

                String details = "";

                for (int i = 0; i < kebabbutikkers.size(); i++) {
                    String name = kebabbutikkers.get(i).getName();

                    details += "\nName: " + name;
                }
                System.out.println(details);
                hidepDialog();
            }

            @Override
            public void onFailure(Throwable t) {
                hidepDialog();
            }
        });
    }
}
