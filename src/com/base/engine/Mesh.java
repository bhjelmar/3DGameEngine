package com.base.engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Mesh {

	private int vbo;
	private int vao;
	private int ibo;

	private int size;

	public Mesh(String filename) {
		initMeshData();
		loadMesh(filename);
	}

	public Mesh(Vertex[] vertices, int[] indices) {
		this(vertices, indices, false);
	}

	public Mesh(Vertex[] vertices, int[] indices, boolean calcNormals) {
		initMeshData();
		addVertices(vertices, indices, calcNormals);
	}

	private void initMeshData() {
		vao = glGenVertexArrays();
		vbo = glGenBuffers();
		ibo = glGenBuffers();
		size = 0;
	}

	private void addVertices(Vertex[] vertices, int[] indices) {
		addVertices(vertices, indices, false);
	}

	public void addVertices(Vertex[] vertices, int[] indices, boolean calcNormals) {
		if(calcNormals) {
			calcNormals(vertices, indices);
		}

		glBindVertexArray(vao);
		size = indices.length;

		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, Util.createFlippedBuffer(vertices), GL_STATIC_DRAW);

		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, Util.createFlippedBuffer(indices), GL_STATIC_DRAW);

	}

	public void draw() {
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glEnableVertexAttribArray(2);

		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, Vertex.SIZE * 4, Float.BYTES * 3);
		glVertexAttribPointer(2, 3, GL_FLOAT, false, Vertex.SIZE * 4, Float.BYTES * 5);

		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glDrawElements(GL_TRIANGLES, size, GL_UNSIGNED_INT, 0);

		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glDisableVertexAttribArray(2);
	}

	private void calcNormals(Vertex[] vertices, int[] indices) {
		for(int i = 0; i < indices.length; i += 3) {
			int i0 = indices[i];
			int i1 = indices[i + 1];
			int i2 = indices[i + 2];

			Vector3f v1 = vertices[i1].getPos().sub(vertices[i0].getPos());
			Vector3f v2 = vertices[i2].getPos().sub(vertices[i0].getPos());

			Vector3f normal = v1.cross(v2).normalized();

			vertices[i0].setNormal(vertices[i0].getNormal().add(normal));
			vertices[i1].setNormal(vertices[i1].getNormal().add(normal));
			vertices[i2].setNormal(vertices[i2].getNormal().add(normal));
		}

		for(int i = 0; i < vertices.length; i++) {
			vertices[i].setNormal(vertices[i].getNormal().normalized());
		}
	}

	private void loadMesh(String filename) {
		String[] splitArray = filename.split("\\.");
		String ext = splitArray[splitArray.length - 1];
		if(!ext.equals("obj")) {
			System.out.println("Error, file format not supported for mesh data: " + ext);
			new Exception().printStackTrace();
			System.exit(1);
		}

		List<Vertex> vertices = new ArrayList<>();
		List<Integer> indices = new ArrayList<>();

		BufferedReader meshReader = null;

		try {
			meshReader = new BufferedReader(new FileReader("./res/models/" + filename));
			String line = null;
			while((line = meshReader.readLine()) != null) {
				String[] tokens = line.split(" ");
				tokens = Util.removeEmptyStrings(tokens);
				if(tokens.length == 0 || tokens[0].equals("#")) {
					continue;
				}
				if(tokens[0].equals("v")) {
					vertices.add(new Vertex(new Vector3f(Float.valueOf(tokens[1]),
							Float.valueOf(tokens[2]),
							Float.valueOf(tokens[3]))));
				}
				else if(tokens[0].equals("f")) {
					indices.add(Integer.parseInt(tokens[1].split("/")[0]) - 1);
					indices.add(Integer.parseInt(tokens[2].split("/")[0]) - 1);
					indices.add(Integer.parseInt(tokens[3].split("/")[0]) - 1);

					if(tokens.length > 4) {
						indices.add(Integer.parseInt(tokens[1].split("/")[0]) - 1);
						indices.add(Integer.parseInt(tokens[2].split("/")[0]) - 1);
						indices.add(Integer.parseInt(tokens[3].split("/")[0]) - 1);
					}
				}
			}
			meshReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Vertex[] vertexData = new Vertex[vertices.size()];
		vertices.toArray(vertexData);

		Integer[] indexData = new Integer[indices.size()];
		indices.toArray(indexData);

		addVertices(vertexData, Util.toIntArray(indexData));
	}

}
