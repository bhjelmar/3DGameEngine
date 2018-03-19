package com.base.engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ResourceLoader {

	public static String loadShader(String filename) {
		StringBuilder shaderSource = new StringBuilder();

		BufferedReader shaderReader = null;

		try {
			shaderReader = new BufferedReader(new FileReader("./res/shaders/" + filename));
			String line;
			while((line = shaderReader.readLine()) != null) {
				shaderSource.append(line).append("\n");
			}
			shaderReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return shaderSource.toString();
	}

	public static Mesh loadMesh(String filename) {
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

		Mesh res = new Mesh();
		res.addVertices(vertexData, Util.toIntArray(indexData));

		return res;
	}

}
