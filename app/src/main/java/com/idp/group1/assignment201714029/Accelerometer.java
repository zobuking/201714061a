package com.idp.group1.assignment201714029;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Accelerometer extends Fragment implements SensorEventListener {

	private TextView xAxisText, yAxisText, zAxisText;

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

		SensorManager sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
		Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

		sensorManager.registerListener(this, accelerometer, sensorManager.SENSOR_DELAY_NORMAL);

		xAxisText = (TextView) view.findViewById(R.id.xAxisID);
		yAxisText = (TextView) view.findViewById(R.id.yAxisID);
		zAxisText = (TextView) view.findViewById(R.id.zAxisID);

		return view;
	}

	@Override
	public void onSensorChanged(SensorEvent sensorEvent) {
		double x = (double) sensorEvent.values[0];
		double y = (double) sensorEvent.values[1];
		double z = (double) sensorEvent.values[2];

		x = Math.round(x * 1000.0) / 1000.0;
		y = Math.round(y * 1000.0) / 1000.0;
		z = Math.round(z * 1000.0) / 1000.0;

		xAxisText.setText(String.valueOf(x));
		yAxisText.setText(String.valueOf(y));
		zAxisText.setText(String.valueOf(z));

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int i) {

	}
}
