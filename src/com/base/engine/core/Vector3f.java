package com.base.engine.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @AllArgsConstructor
public class Vector3f {

	private float x;
	private float y;
	private float z;

	public float length() {
		return (float) Math.sqrt((Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)));
	}

	public float dot(Vector3f v) {
		return x * v.getX() + y * v.getY() + z * v.getZ();
	}

	public Vector3f cross(Vector3f v) {
		float x_ = y * v.getZ() - z * v.getY();
		float y_ = z * v.getX() - x * v.getZ();
		float z_ = x * v.getY() - y * v.getX();

		return new Vector3f(x_, y_, z_);
	}

	public Vector3f normalized() {
		float length = length();
		return new Vector3f(this.x /= length, this.y /= length, this.z /= length);
	}

	public Vector3f rotate(float angle, Vector3f axis) {
		float sinHalfAngle = (float) Math.sin(Math.toRadians(angle / 2));
		float cosHalfAngle = (float) Math.cos(Math.toRadians(angle / 2));

		float rX = axis.getX() * sinHalfAngle;
		float rY = axis.getY() * sinHalfAngle;
		float rZ = axis.getZ() * sinHalfAngle;
		float rW = cosHalfAngle;

		Quaternion rotation = new Quaternion(rX, rY, rZ, rW);
		Quaternion conjugate = rotation.conjugate();

		Quaternion w = rotation.mult(this).mult(conjugate);

		return new Vector3f(w.getX(), w.getY(), w.getZ());
	}

	public Vector3f add(Vector3f v) {
		return new Vector3f(x + v.getX(), y + v.getY(), z + v.getZ());
	}

	public Vector3f add(float f) {
		return new Vector3f(x + f, y + f, z + f);
	}

	public Vector3f sub(Vector3f v) {
		return new Vector3f(x - v.getX(), y - v.getY(), z - v.getZ());
	}

	public Vector3f sub(float f) {
		return new Vector3f(x - f, y - f, z - f);
	}

	public Vector3f mult(Vector3f v) {
		return new Vector3f(x * v.getX(), y * v.getY(), z * v.getZ());
	}

	public Vector3f mult(float f) {
		return new Vector3f(x * f, y * f, z * f);
	}

	public Vector3f div(Vector3f v) {
		return new Vector3f(x / v.getX(), y / v.getY(), z / v.getZ());
	}

	public Vector3f div(float f) {
		return new Vector3f(x / f, y / f, z / f);
	}

	public Vector3f abs() {
		return new Vector3f(Math.abs(x), Math.abs(y), Math.abs(z));
	}
}
