package com.base.engine.rendering;

import com.base.engine.core.Matrix4f;
import com.base.engine.core.Transform;
import lombok.Getter;

public class ForwardAmbient extends Shader {

	@Getter
	private static final ForwardAmbient instance = new ForwardAmbient();

	private ForwardAmbient() {
		addVertexShaderFromFile("forward-ambient.vert");
		addFragmentShaderFromFile("forward-ambient.frag");

		setAttribLocation("position", 0);
		setAttribLocation("textCoord", 1);

		compileShader();

		addUniform("MVP");
		addUniform("ambientIntensity");
	}

	public void updateUniforms(Transform transform, Material material) {
		Matrix4f worldMatrix = transform.getTransformation();
		Matrix4f projectedMatrix = getRenderingEngine().getMainCamera().getViewProjection().mul(worldMatrix);


		material.getTexture().bind();

		setUniform("MVP", projectedMatrix);
		setUniform("ambientIntensity", getRenderingEngine().getAmbientLight());
	}

}
