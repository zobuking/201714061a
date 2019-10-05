package com.idp.group1.assignment201714029;

import androidx.annotation.NonNull;

public class Value {
	public double x, y, z;

	public Value(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Value() {
	}

	@NonNull
	@Override
	public String toString() {
		return ("x = " + x + " y = " + y + " z = " + z);
	}
}
