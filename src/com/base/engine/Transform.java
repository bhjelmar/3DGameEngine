package com.base.engine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Transform {

	private static  Camera camera;

	//how close/far an object can be before clipping
	private static float zNear;
	private static float zFar;
	private static float width;
	private static float height;
	private static float fov;

	private Vector3f translation;
	private Vector3f rotation;
	private Vector3f scale;

	public Transform() {
		translation = new Vector3f(0.0f, 0.0f, 0.0f);
		rotation = new Vector3f(0.0f, 0.0f, 0.0f);
		scale = new Vector3f(1.0f, 1.0f, 1.0f);
	}

	public Matrix4f getTransformation() {
		Matrix4f translationMatrix = new Matrix4f().initTranslation(translation.getX(), translation.getY(), translation.getZ());
		Matrix4f rotationMatrix = new Matrix4f().initRotation(rotation.getX(), rotation.getY(), rotation.getZ());
		Matrix4f scaleMatrix = new Matrix4f().initScale(scale.getX(), scale.getY(), scale.getZ());
		return translationMatrix.mul(rotationMatrix.mul(scaleMatrix));
	}

	public Matrix4f getProjectedTransformation() {
		Matrix4f transformationMatrix = getTransformation();
		Matrix4f projectionMatrix = new Matrix4f().initProjection(fov, width, height, zNear, zFar);
//		Matrix4f cameraMatrix = new Matrix4f().initCamera();

		return projectionMatrix.mul(transformationMatrix);
	}

	public static void setProjection(float fov, float width, float height, float zNear, float zFar) {
		Transform.fov = fov;
		Transform.width = width;
		Transform.height = height;
		Transform.zNear = zNear;
		Transform.zFar = zFar;
	}

	public void setTranslation(float x, float y, float z) {
		this.translation = new Vector3f(x, y, z);
	}

	public void setRotation(float x, float y, float z) {
		this.rotation = new Vector3f(x, y, z);
	}

	public void setScale(float x, float y, float z) {
		this.scale = new Vector3f(x, y, z);
	}

}
