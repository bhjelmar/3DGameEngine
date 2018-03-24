package com.base.engine.rendering;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.File;
import java.io.FileInputStream;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;

@Getter @Setter @ToString @AllArgsConstructor
public class Texture {

	private int id;

	public Texture(String filename) {
		this(loadTexture(filename));
	}

	public void bind() {
		glBindTexture(GL_TEXTURE_2D, id);
	}

	private static int loadTexture(String filename) {
		String[] splitArray = filename.split("\\.");
		String ext = splitArray[splitArray.length - 1];

		try {
			int id = TextureLoader.getTexture(ext, new FileInputStream(new File("./res/textures/" + filename))).getTextureID();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		return 0;
	}

}
