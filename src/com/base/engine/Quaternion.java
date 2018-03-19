package com.base.engine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @AllArgsConstructor
public class Quaternion {

	private float x;
	private float y;
	private float z;
	private float w;

	public float length() {
		return (float) Math.sqrt((Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2) + Math.pow(w, 2)));
	}

	public Quaternion normalize() {
		return new Quaternion(x /= length(), y /= length(), z / length(), w / length());
	}

	public Quaternion conjugate() {
		return new Quaternion(-x, -y, -z, w);
	}

	public Quaternion mult(Quaternion r) {
		float w_ = w * r.getW() - x * r.getX() - y * r.getY() - z * r.getZ();
		float x_ = x * r.getW() + w * r.getX() + y * r.getZ() - z * r.getY();
		float y_ = y * r.getW() + w * r.getY() + z * r.getX() - x * r.getX();
		float z_ = z * r.getW() + w * r.getZ() + x * r.getY() - y * r.getZ();

		return new Quaternion(x_, y_, z_, w_);
	}

	public Quaternion mult(Vector3f r) {
		float w_ = -x * r.getX() - y * r.getY() - z * r.getZ();
		float x_ =  w * r.getX() + y * r.getZ() - z * r.getY();
		float y_ =  w * r.getY() + z * r.getX() - x * r.getZ();
		float z_ =  w * r.getZ() + x * r.getY() - y * r.getX();

		return new Quaternion(x_, y_, z_, w_);
	}

}
