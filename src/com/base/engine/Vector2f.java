package com.base.engine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @AllArgsConstructor
public class Vector2f {

	private float x;
	private float y;

	public float length() {
		return (float) Math.sqrt((Math.pow(x, 2) + Math.pow(y, 2)));
	}

	public float dot(Vector2f v) {
		return x * v.getX() + y * v.getY();
	}

	public Vector2f normalized() {
		float length = length();
		return new Vector2f(this.x /= length, this.y /= length);
	}

	public Vector2f rotate(float angle) {
		double rad = Math.toRadians(angle);
		double cos = Math.cos(rad);
		double sin = Math.sin(rad);

		return new Vector2f((float) ((cos * x) - (sin * y)),(float) ((sin * x) + (cos * y)));
	}

	public Vector2f add(Vector2f v) {
		return new Vector2f(x + v.getX(), y + v.getY());
	}

	public Vector2f add(float f) {
		return new Vector2f(x + f, y + f);
	}

	public Vector2f sub(Vector2f v) {
		return new Vector2f(x - v.getX(), y - v.getY());
	}

	public Vector2f sub(float f) {
		return new Vector2f(x - f, y - f);
	}

	public Vector2f mult(Vector2f v) {
		return new Vector2f(x * v.getX(), y * v.getY());
	}

	public Vector2f mult(float f) {
		return new Vector2f(x * f, y * f);
	}

	public Vector2f div(Vector2f v) {
		return new Vector2f(x / v.getX(), y / v.getY());
	}

	public Vector2f div(float f) {
		return new Vector2f(x / f, y / f);
	}

}
