package com.base.engine;

import org.lwjgl.input.Keyboard;

public class Game {

	private Mesh mesh;
	private Shader shader;
	private Transform transform;
	private Texture texture;
	private Camera camera;

	public Game() {
//		mesh = new Mesh();
		texture = ResourceLoader.loadTexture("text.png");
//		System.out.println("texture:" + texture.getId());
//		Vertex[] vertices = new Vertex[] {new Vertex(new Vector3f(-1,-1,0), new Vector2f(0,0)),
//							  new Vertex(new Vector3f(0,1,0), new Vector2f(.5f,0)),
//							  new Vertex(new Vector3f(1,-1,0), new Vector2f(1.0f,0)),
//							  new Vertex(new Vector3f(0,-1,1), new Vector2f(0,0.5f))};
//		int[] indices = new int[] {3,1,0,
//								   2,1,3,
//								   0,1,2,
//								   0,2,3};
//		mesh.addVertices(vertices, indices);

//		mesh = ResourceLoader.loadMesh("box.obj");
		mesh = ResourceLoader.loadMesh("IronMan.obj");

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
//		RenderUtil.setClearColor(Transform.getCamera().getPos().div(2048f).abs());
		shader.bind();
		texture.bind();
		shader.setUniform("transform", transform.getProjectedTransformation());
		mesh.draw();
	}
}