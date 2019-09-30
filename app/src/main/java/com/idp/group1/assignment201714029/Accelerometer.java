package com.idp.group1.assignment201714029;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Accelerometer extends Fragment implements SensorEventListener {

	private TextView xAxisAccelerometerText, yAxisAccelerometerText, zAxisAccelerometerText;
	private LinearLayout linearLayout;
	private SensorManager sensorManager;
	private Sensor accelerometer;

	public Accelerometer() {
		// Required empty public constructor
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_accelerometer, container, false);
		linearLayout = view.findViewById(R.id.accelerometerID);
//		fragment = (Fragment) getFragmentManager().findFragmentById(R.id.accelerometerID);


		sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
		accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sensorManager.registerListener(this, accelerometer, sensorManager.SENSOR_DELAY_NORMAL);

		if (accelerometer == null) {
			Toast.makeText(getContext(), "No Accelerometer Sensor Found", Toast.LENGTH_SHORT).show();
		}
		else {
			Toast.makeText(getContext(), "Accelerometer Sensor Working", Toast.LENGTH_SHORT).show();
		}

		xAxisAccelerometerText = (TextView) view.findViewById(R.id.xAxisAccelerometerID);
		yAxisAccelerometerText = (TextView) view.findViewById(R.id.yAxisAccelerometerID);
		zAxisAccelerometerText = (TextView) view.findViewById(R.id.zAxisAccelerometerID);

		return view;
	}

	@Override
	public void onSensorChanged(SensorEvent sensorEvent) {
		double xAccelerometer = (double) sensorEvent.values[0];
		double yAccelerometer = (double) sensorEvent.values[1];
		double zAccelerometer = (double) sensorEvent.values[2];

//		x = x * 57.2957795f;
//		y = y * 57.2957795f;
//		z = z * 57.2957795f;

		if (yAccelerometer <= 5.0 && yAccelerometer >= -5.0) {
//			Toast.makeText(getContext(), "entered", Toast.LENGTH_SHORT).show();
			linearLayout.setBackgroundColor(Color.CYAN);
		}
		else {
			linearLayout.setBackgroundColor(Color.WHITE);
		}

		xAccelerometer = Math.round(xAccelerometer * 1000.0) / 1000.0;
		yAccelerometer = Math.round(yAccelerometer * 1000.0) / 1000.0;
		zAccelerometer = Math.round(zAccelerometer * 1000.0) / 1000.0;

		xAxisAccelerometerText.setText(String.valueOf(xAccelerometer));
		yAxisAccelerometerText.setText(String.valueOf(yAccelerometer));
		zAxisAccelerometerText.setText(String.valueOf(zAccelerometer));

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int i) {

	}

	@Override
	public void onResume() {
		super.onResume();
		sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	public void onPause()
	{
		super.onPause();
		sensorManager.unregisterListener(this);
	}

}
