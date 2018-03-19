package com.base.engine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;

@Getter @Setter @ToString @AllArgsConstructor
public class Texture {

	private int id;

	public void bind() {
		glBindTexture(GL_TEXTURE_2D, id);
	}

}
