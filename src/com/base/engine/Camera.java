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

	boolean mouseLocked = false;
	Vector2f centerPosition = new Vector2f(Window.getWidth() / 2, Window.getHeight() / 2);

	public Camera() {
		this(new Vector3f(0, 0, 0), new Vector3f(0, 0, 1), new Vector3f(0, 1, 0));
	}

	public Camera(Vector3f pos, Vector3f forward, Vector3f up) {
		this.pos = pos;
		this.forward = forward.normalized();
		this.up = up.normalized();
	}

	public void input() {
		float mouseSensitivity = 0.25f;
		float moveAmount = (float) (10 * Time.getDelta());

		if(Input.getKey(Keyboard.KEY_ESCAPE)) {
			Input.setCursor(true);
			mouseLocked = false;
		}
		if(Input.getMouseDown(0)) {
			Input.setMousePosition(centerPosition);
			Input.setCursor(false);
			mouseLocked = true;
		}

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

		if(mouseLocked) {
			Vector2f deltaPos = Input.getMousePosition().sub(centerPosition);

			boolean rotY = deltaPos.getX() != 0;
			boolean rotX = deltaPos.getY() != 0;

			if(rotY) {
				rotateY(deltaPos.getX() * mouseSensitivity);
			}
			if(rotX) {
				rotateX(-deltaPos.getY() * mouseSensitivity);
			}
			if(rotX || rotY) {
				Input.setMousePosition(new Vector2f(Window.getWidth() / 2, Window.getHeight() / 2));
			}
		}
	}

	public void move(Vector3f dir, float amt) {
		pos = pos.add(dir.mult(amt));
	}

	public void rotateY(float angle) {
		Vector3f hAxis = yAxis.cross(forward).normalized();
		forward = forward.rotate(angle, yAxis).normalized();
		up = forward.cross(hAxis).normalized();
	}

	public void rotateX(float angle) {
		Vector3f hAxis = yAxis.cross(forward).normalized();
		forward = forward.rotate(angle, hAxis).normalized();
		up = forward.cross(hAxis).normalized();
	}

	public Vector3f getLeft() {
		return forward.cross(up).normalized();
	}

	public Vector3f getRight() {
		return up.cross(forward).normalized();
	}

}