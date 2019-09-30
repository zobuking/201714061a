package com.idp.group1.assignment201714029;


import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Shake_detection extends Fragment implements SensorEventListener{

	private SensorManager sensorManager;
	private double curAccerValue, lastAccerValue, shake;
	private Sensor accelerometer;

	public Shake_detection() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view =  inflater.inflate(R.layout.fragment_shake_detection, container, false);

		sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
		accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sensorManager.registerListener((SensorEventListener) this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

		curAccerValue = SensorManager.GRAVITY_EARTH;
		lastAccerValue = SensorManager.GRAVITY_EARTH;
		shake = 0.00f;

		return view;
	}


	@Override
	public void onSensorChanged(SensorEvent sensorEvent) {
		double x = sensorEvent.values[0], y = sensorEvent.values[1], z = sensorEvent.values[2];

		lastAccerValue = curAccerValue;
		curAccerValue = (double) Math.sqrt(x*x + y*y + z*z);
		double delta = curAccerValue - lastAccerValue;

		shake = shake*0.9f + delta;

		if (shake > 5) {
			Toast.makeText(getContext(), "shake detected", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(Intent.ACTION_DIAL);
			intent.setData(Uri.parse("tel:911"));
			startActivity(intent);
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int i) {

	}
}
