package com.base.engine.rendering;

import com.base.engine.core.*;
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

	private Matrix4f projection;

	boolean mouseLocked = false;
	Vector2f centerPosition = new Vector2f(Window.getWidth() / 2, Window.getHeight() / 2);

	public Camera(float fov, float aspectRatio, float zNear, float zFar) {
		this.pos = new Vector3f(0, 0, 0);
		this.forward = new Vector3f(0, 0, 1).normalized();
		this.up = new Vector3f(0, 1, 0).normalized();
		this.projection = new Matrix4f().initPerspective(fov, aspectRatio, zNear, zFar);
	}

	public Matrix4f getViewProjection() {
		Matrix4f cameraRotation = new Matrix4f().initRotation(forward, up);
		Matrix4f cameraTranslation = new Matrix4f().initTranslation(-pos.getX(), -pos.getY(), -pos.getZ());

		return projection.mul(cameraRotation.mul(cameraTranslation));
	}

	public void input(float delta) {
		float mouseSensitivity = 0.15f;
		float moveAmount = 25 * delta;

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