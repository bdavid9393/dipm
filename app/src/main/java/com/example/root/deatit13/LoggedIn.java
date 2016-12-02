package com.example.root.deatit13;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

/**
 * Created by root on 30.11.16.
 */

public class LoggedIn extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {


    private Button btn_logout;
    private TextView tv_welcome;
    private TextView tv_loc;

    protected Location mLastLocation;
    private User user;
    protected GoogleApiClient mGoogleApiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_logged_in);

        user = (User) getIntent().getSerializableExtra("user");

        tv_welcome = (TextView) findViewById(R.id.tv_welcome);
        tv_welcome.setText("Udvozoljuk\n" + user.getEmail() + "\n" + user.getName());

        tv_loc = (TextView) findViewById(R.id.tv_loc);

        btn_logout = (Button) findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(this);

        buildGoogleApiClient();


    }

    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_logout:
                finish();
                break;


        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (mLastLocation != null) {


            user.setLastKnownPos(mLastLocation);
            tv_loc.setText(user.getLastKnownPos().getLatitude() + "   " + user.getLastKnownPos().getLongitude());

            Toast.makeText(this, "Lokacio sikeres!", Toast.LENGTH_LONG).show();


        } else {
            Toast.makeText(this, "Lokacio sikertelen!", Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override

    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Failed to connect google...", Toast.LENGTH_LONG).show();


    }


}
