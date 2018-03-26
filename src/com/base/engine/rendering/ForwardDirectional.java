package com.base.engine.rendering;

import com.base.engine.core.Matrix4f;
import com.base.engine.core.Transform;
import lombok.Getter;

public class ForwardDirectional extends Shader {

	@Getter
	private static final ForwardDirectional instance = new ForwardDirectional();

	private ForwardDirectional() {
		addVertexShaderFromFile("forward-directional.vert");
		addFragmentShaderFromFile("forward-directional.frag");

		setAttribLocation("position", 0);
		setAttribLocation("textCoord", 1);
		setAttribLocation("normal", 2);

		compileShader();

		addUniform("model");
		addUniform("MVP");

		addUniform("specularIntensity");
		addUniform("specularPower");

		addUniform("eyePos");

		addUniform("directionalLight.base.color");
		addUniform("directionalLight.base.intensity");
		addUniform("directionalLight.direction");

	}

	public void updateUniforms(Transform transform, Material material) {
		Matrix4f worldMatrix = transform.getTransformation();
		Matrix4f projectedMatrix = getRenderingEngine().getMainCamera().getViewProjection().mul(worldMatrix);


		material.getTexture().bind();

		setUniform("model", worldMatrix);
		setUniform("MVP", projectedMatrix);

		setUniformf("specularIntensity", material.getSpecularIntensity());
		setUniformf("specularPower", material.getSpecularPower());

		setUniform("eyePos", getRenderingEngine().getMainCamera().getPos());

		setUniform("directionalLight", getRenderingEngine().getDirectionalLight());
	}

	public void setUniform(String uniformName, BaseLight baseLight) {
		setUniform(uniformName + ".color", baseLight.getColor());
		setUniformf(uniformName + ".intensity", baseLight.getIntensity());
	}

	public void setUniform(String uniformName, DirectionalLight directionalLight) {
		setUniform(uniformName + ".base", directionalLight.getBaseLight());
		setUniform(uniformName + ".direction", directionalLight.getDirection());
	}

}
