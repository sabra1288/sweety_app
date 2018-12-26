package firstapp.om.sweety;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private GeoDataClient geoDataClient;
    private PlaceDetectionClient placeDetectionClient;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private boolean mLocationPermission=false;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION=1;
    private Location mLastKnowLocation;
    private static final float DEFAULT_ZOOM =0.8f ;
    private final LatLng mDefaultLocation=new LatLng(-34, 151);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        geoDataClient = Places.getGeoDataClient(this,null);
        placeDetectionClient=Places.getPlaceDetectionClient(this,null);
        fusedLocationProviderClient=LocationServices.getFusedLocationProviderClient(this);





        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    private void getLocationPermission(){
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){

        }else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermission=false;
        switch (requestCode){
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION:{
                if (grantResults.length>0 &&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    mLocationPermission=true;
                }
            }
        }
        upDateLoactionUI();
    }

    private void upDateLoactionUI(){
        if (mMap ==null){
            return;
        }
        try{
            if (mLocationPermission){
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);

            }else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnowLocation=null;
                getLocationPermission();
            }
        }catch (SecurityException e){
            Log.e("Exception:%s",e.getMessage());
        }
    }
    private void getDeviceLocation(){
        try{
            if (mLocationPermission){
                Task taskResult=fusedLocationProviderClient.getLastLocation();
                taskResult.addOnCompleteListener(this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()){
                            mLastKnowLocation=(Location)task.getResult();
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new
                                    LatLng(mLastKnowLocation.getLatitude(),mLastKnowLocation.getAltitude()),DEFAULT_ZOOM));

                        }else {
                            Log.d("TAG","Current locationis null.Using default");
                            Log.e("TAG","Exception:%s",task.getException());
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation,DEFAULT_ZOOM));
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);


                        }

                    }
                });


            }else {

            }

        }
        catch (SecurityException e){
            Log.e("Exception:%s",e.getMessage());
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        upDateLoactionUI();
        getDeviceLocation();
    }
}
