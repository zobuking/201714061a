package com.idp.group1.assignment201714029;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class Proximity extends Fragment implements SensorEventListener {

	private TextView xAxisProximityText, yAxisProximityText;
	private SensorManager sensorManager;
	private Sensor proximity;
	private Vibrator vibrator;

	public Proximity() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view =  inflater.inflate(R.layout.fragment_proximity, container, false);

		vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
		sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
		proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

		if (proximity == null) {
			Toast.makeText(getContext(), "No Proximity Sensor Found", Toast.LENGTH_SHORT).show();
		}
		else {
			Toast.makeText(getContext(), "Proximity Sensor Working", Toast.LENGTH_SHORT).show();
		}

		xAxisProximityText = view.findViewById(R.id.xAxisProximityID);
		yAxisProximityText = view.findViewById(R.id.yAxisProximityID);

		return view;
	}

	@Override
	public void onSensorChanged(SensorEvent sensorEvent) {

		xAxisProximityText.setText(String.valueOf(sensorEvent.values[0]));
		yAxisProximityText.setText(String.valueOf(proximity.getMaximumRange()));

		if (sensorEvent.values[0] < proximity.getMaximumRange()) {
			Toast.makeText(getContext(), "Vibrating", Toast.LENGTH_SHORT).show();
			vibrator.vibrate(500);
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int i) {

	}

	@Override
	public void onResume()
	{
		super.onResume();
		sensorManager.registerListener(this,proximity,SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	public void onPause()
	{
		super.onPause();
		sensorManager.unregisterListener(this);
	}

}
