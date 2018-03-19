package com.base.engine;

import org.lwjgl.input.Keyboard;

public class Game {

	private Mesh mesh;
	private Shader shader;
	private Transform transform;
	private Camera camera;

	public Game() {
		mesh = ResourceLoader.loadMesh("box.obj");
//		mesh = ResourceLoader.loadMesh("IronMan.obj");

		shader = new Shader();
		camera = new Camera();

		Transform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 1000);
		Transform.setCamera(camera);
		transform = new Transform();

		shader.addVertexShader(ResourceLoader.loadShader("basicVertex.vs"));
		shader.addFragmentShader(ResourceLoader.loadShader("basicFragment.fs"));
		shader.compileShader();

		shader.addUniform("transform");
	}

	public void input() {
		camera.input();

//		if(Input.getKeyDown(Keyboard.KEY_UP)) {
//			transform.setTranslation(0,0,0);
//		}
//		if(Input.getKeyDown(Keyboard.KEY_DOWN)) {
//			transform.setTranslation(0,-.01f,0);
//		}
//		if(Input.getKeyUp(Keyboard.KEY_UP)) {
//			System.out.println("released up");
//		}
//
//		if(Input.getMouseDown(1)) {
//			System.out.println("pressed right click at " + Input.getMousePosition());
//		}
//		if(Input.getMouseUp(1)) {
//			System.out.println("released right click at " + Input.getMousePosition());
//		}
	}

	float temp = 0.0f;

	public void update() {
		temp += Time.getDelta();
		float sinTemp = (float) Math.sin(temp);
		transform.setTranslation(0, 0, 5);
		transform.setRotation(sinTemp * 180, sinTemp * 180, sinTemp * 180);
//		transform.setScale(0.7f * sinTemp, 0.7f * sinTemp, 0.7f * sinTemp);

	}

	public void render() {
		shader.bind();
		shader.setUniform("transform", transform.getProjectedTransformation());
		mesh.draw();
	}
}