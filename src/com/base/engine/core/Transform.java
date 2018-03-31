package com.base.engine.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Transform {

	private Vector3f pos;
	private Quaternion rot;
	private Vector3f scale;

	public Transform() {
		pos = new Vector3f(0, 0, 0);
		rot = new Quaternion(0, 0, 0, 1);
		scale = new Vector3f(1, 1, 1);
	}

	public Matrix4f getTransformation() {
		Matrix4f translationMatrix = new Matrix4f().initTranslation(pos.getX(), pos.getY(), pos.getZ());
		Matrix4f rotationMatrix = null;//rot.toRotationMatrix();//new Matrix4f().initRotation(rot.getX(), rot.getY(), rot.getZ());
		Matrix4f scaleMatrix = new Matrix4f().initScale(scale.getX(), scale.getY(), scale.getZ());
		return translationMatrix.mul(rotationMatrix.mul(scaleMatrix));
	}

	public void setTranslation(float x, float y, float z) {
		this.pos = new Vector3f(x, y, z);
	}

	public void setRotation(float x, float y, float z, float w) {
		this.rot = new Quaternion(x, y, z, w);
	}

	public void setScale(float x, float y, float z) {
		this.scale = new Vector3f(x, y, z);
	}

}
