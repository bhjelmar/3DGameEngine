package com.base.engine;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.lwjgl.input.Keyboard;

@Getter @Setter @ToString
public class Camera {

	public static final Vector3f yAxis = new Vector3f(0,1,0);

	private Vector3f pos;
	private Vector3f forward;
	private Vector3f up;

	public Camera() {
		this(new Vector3f(0, 0, 0), new Vector3f(0, 0, 1), new Vector3f(0, 1, 0));
	}

	public Camera(Vector3f pos, Vector3f forward, Vector3f up) {
		this.pos = pos;
		this.forward = forward;
		this.up = up;

		up.normalize();
		forward.normalize();
	}

	public void input() {
		float moveAmount = (float) (10 * Time.getDelta());
		float rotAmount = (float) (10 * Time.getDelta());

		if(Input.getKey(Keyboard.KEY_W)) {
			move(getForward(), moveAmount);
		}
		if(Input.getKey(Keyboard.KEY_S)) {
			move(getForward(), -moveAmount);
		}
		if(Input.getKey(Keyboard.KEY_A)) {
			move(getLeft(), moveAmount);
		}
		if(Input.getKey(Keyboard.KEY_D)) {
			move(getRight(), moveAmount);
		}

		if(Input.getKey(Keyboard.KEY_UP)) {
			rotateX(-rotAmount);
		}
		if(Input.getKey(Keyboard.KEY_DOWN)) {
			rotateX(rotAmount);
		}
		if(Input.getKey(Keyboard.KEY_LEFT)) {
			rotateY(-rotAmount);
		}
		if(Input.getKey(Keyboard.KEY_RIGHT)) {
			rotateY(rotAmount);
		}
	}

	public void move(Vector3f dir, float amt) {
		pos = pos.add(dir.mult(amt));
	}

	public void rotateY(float angle) {
		Vector3f hAxis = yAxis.cross(forward);
		hAxis.normalize();

		forward.rotate(angle, yAxis);
		forward.normalize();

		up = forward.cross(hAxis);
		up.normalize();
	}

	public void rotateX(float angle) {
		Vector3f hAxis = yAxis.cross(forward);
		hAxis.normalize();

		forward.rotate(angle, hAxis);
		forward.normalize();

		up = forward.cross(hAxis);
		up.normalize();
	}

	public Vector3f getLeft() {
		Vector3f left = forward.cross(up);
		left.normalize();
		return left;
	}

	public Vector3f getRight() {
		Vector3f right = up.cross(forward);
		right.normalize();
		return right;
	}

}