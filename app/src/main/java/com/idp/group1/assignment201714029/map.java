package com.idp.group1.assignment201714029;


import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class map extends Fragment implements OnMapReadyCallback {

	private GoogleMap mMap;
	private FloatingActionButton floatingActionButton;
	private FusedLocationProviderClient fusedLocationProviderClient;
	private double latitude = -34.0, longitude = 151.0;

	public map() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view =  inflater.inflate(R.layout.fragment_map, container, false);

		fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

		floatingActionButton = view.findViewById(R.id.floatingButtonID);
		floatingActionButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				getLocation();
			}
		});

		return view;
	}

	private void getLocation() {
		Toast.makeText(getContext(), "button Clicked", Toast.LENGTH_SHORT).show();
		fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
			@Override
			public void onSuccess(Location location) {
				if (location != null) {
					latitude = location.getLatitude();
					longitude = location.getLongitude();

					Toast.makeText(getContext(), latitude + " " + longitude, Toast.LENGTH_SHORT).show();
				}
				else {
					Toast.makeText(getContext(), "location is null", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
				.findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);
	}

	@Override
	public void onMapReady(GoogleMap googleMap) {
		mMap = googleMap;

		// Add a marker in Sydney and move the camera
		LatLng sydney = new LatLng(latitude, longitude);
		mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
		mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
	}
}
