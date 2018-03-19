package com.base.engine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @AllArgsConstructor
public class Vertex {

	public static final int SIZE = 3;
	private Vector3f pos;

}
