package com.example.framgia.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

/**
 * Remember to change library in gradle
 * Mapion use library in libs/MapboxGLAndroidSDK-release.aar
 * Mapbox use library in 'com.mapbox.mapboxsdk:mapbox-android-sdk:5.0.2@aar'
 */
public class SecondActivity extends AppCompatActivity {
    String Mapion_Key = "mt-pk"
            + ".eyJ1IjoibWFwaW9uIiwiYSI6InJ5ZXFiOWJyZ3J2MjV3ZDcyNXY3Z3dmYzYifQ"
            + ".XoOA7fe-5nUi8ALSyCl277";

    String Mapbox_Key =
            "pk.eyJ1IjoicGhhbnZhbmxpbmg5NHZuIiwiYSI6ImNqMW44ZmtlbDAwcjYyd28yaDQzbzJwejAifQ.v9ID5IxcItXpaw72ZVN4dA";

    private MapView mapView;
    private MapboxMap mMapboxMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        Mapbox.getInstance(getApplicationContext(), Mapbox_Key);
        Mapbox.getInstance(getApplicationContext(), Mapion_Key);
        setContentView(R.layout.activity_second);

        mapView = (MapView) findViewById(R.id.mapView);
        //TODO need enable 2 line codes below when use mapion key (don't enable -> map black)
        mapView.setCopyright("© Mapion 地図データ © ZENRIN © 北海道地図");
//        mapView.setStyleUrl(
//                "https://www.mapion.co.jp/d/smp-apps/common/mapion-gl-stylels/style-raster-mapion-ssl.json");


//        mapView.setStyleUrl("file:///android_asset/abc.json");
        mapView.setStyleUrl("asset://abc.json");

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                Toast.makeText(SecondActivity.this, "ready", Toast.LENGTH_SHORT).show();
                mMapboxMap = mapboxMap;

                moveToJapan(mapboxMap);
            }
        });
    }

    /**
     * If we use Mapion key we need to move to japan to see the map
     * because the current style of mapion map not display map outside japan
     */
    private void moveToJapan(MapboxMap mapboxMap) {
        CameraPosition currentCameraPosition = mapboxMap.getCameraPosition();
        CameraPosition position = new CameraPosition.Builder(currentCameraPosition).target(
                new LatLng(35.8103467, 139.4821614, 9.79)).zoom(8).build();
        mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
}
