package com.base.engine;

import org.lwjgl.input.Keyboard;

public class Game {

	private Mesh mesh;
	private Shader shader;
	private Material material;
	private Transform transform;
	private Camera camera;

	public Game() {
//		mesh = ResourceLoader.loadMesh("box.obj");
//		mesh = ResourceLoader.loadMesh("IronMan.obj");

		Vertex[] vertices = new Vertex[] {new Vertex(new Vector3f(-1,-1,0), new Vector2f(0,0)),
				  new Vertex(new Vector3f(0,1,0), new Vector2f(0.5f,0)),
				  new Vertex(new Vector3f(1,-1,0), new Vector2f(1.0f,0)),
				  new Vertex(new Vector3f(0,-1,1), new Vector2f(0.5f,1.0f))};

		int[] indices = new int[] {3,1,0,
								   2,1,3,
								   0,1,2,
								   0,2,3};
		mesh = new Mesh();
		mesh.addVertices(vertices, indices);

		material = new Material(ResourceLoader.loadTexture("test.png"), new Vector3f(0, 1, 1));
		shader = PhongShader.getInstance();
		camera = new Camera();

		Transform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 1000);
		Transform.setCamera(camera);
		transform = new Transform();

		PhongShader.setAmbientLight(new Vector3f(.1f, 0.1f, 0.1f));
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
		RenderUtil.setClearColor(Transform.getCamera().getPos().div(2048f).abs());
		shader.bind();
		shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(), material);
		mesh.draw();
	}
}