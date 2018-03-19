package com.base.engine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @AllArgsConstructor
public class Vertex {

	public static final int SIZE = 5;
	private Vector3f pos;
	private Vector2f textCoord;

	public Vertex(Vector3f pos) {
		this(pos, new Vector2f(0, 0));
	}

}
