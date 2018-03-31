package com.base.engine.core;

import lombok.*;

@Getter @Setter @EqualsAndHashCode
@ToString @AllArgsConstructor
public class Vector3f {

	private float x;
	private float y;
	private float z;

	public void set(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public float length() {
		return (float) Math.sqrt((Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)));
	}

	public float dot(Vector3f v) {
		return x * v.getX() + y * v.getY() + z * v.getZ();
	}

	public float max() {
		return Math.max(x, Math.max(y, z));
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

	public Vector3f rotate(Vector3f axis, float angle)
	{
		float sinAngle = (float)Math.sin(-angle);
		float cosAngle = (float)Math.cos(-angle);

		return this.cross(axis.mult(sinAngle)).add(           //Rotation on local X
				(this.mult(cosAngle)).add(                     //Rotation on local Z
						axis.mult(this.dot(axis.mult(1 - cosAngle))))); //Rotation on local Y
	}

	public Vector3f rotate(Quaternion rotation)
	{
		Quaternion conjugate = rotation.conjugate();

		Quaternion w = rotation.mult(this).mult(conjugate);

		return new Vector3f(w.getX(), w.getY(), w.getZ());
	}

	public Vector3f lerp(Vector3f dest, float lerpFactor) {
		return dest.sub(this).mult(lerpFactor).add(this);
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

	public Vector2f getXY() {
		return new Vector2f(x, y);
	}

	public Vector2f getYZ() {
		return new Vector2f(y, z);
	}

	public Vector2f getZX() {
		return new Vector2f(z, x);
	}

	public Vector2f getYX() {
		return new Vector2f(y, x);
	}

	public Vector2f getZY() {
		return new Vector2f(z, y);
	}

	public Vector2f getXZ() {
		return new Vector2f(x, z);
	}

}
