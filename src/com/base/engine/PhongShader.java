package com.base.engine;

import lombok.Getter;
import lombok.Setter;

public class PhongShader extends Shader {

    private static final int MAX_POINT_LIGHTS = 4;

    @Getter
    private static final PhongShader instance = new PhongShader();

    @Getter @Setter
    private static Vector3f ambientLight = new Vector3f(.1f, 0.1f, 0.1f);
    @Getter @Setter
    private static DirectionalLight directionalLight = new DirectionalLight(
            new BaseLight(
                    new Vector3f(1, 1, 1),
                    0
            ),
            new Vector3f(0, 0, 0)
    );
    @Getter
    private static PointLight[] pointLights = new PointLight[] {};

    private PhongShader() {
        super();

        addVertexShader(ResourceLoader.loadShader("phongVertex.vert"));
        addFragmentShader(ResourceLoader.loadShader("phongFragment.frag"));
        compileShader();

        addUniform("transform");
        addUniform("transformProjected");
        addUniform("baseColor");
        addUniform("ambientLight");

        addUniform("directionalLight.base.color");
        addUniform("directionalLight.base.intensity");
        addUniform("directionalLight.direction");

        addUniform("specularIntensity");
        addUniform("specularPower");

        addUniform("eyePos");

        for(int i = 0; i < MAX_POINT_LIGHTS; i++) {
            addUniform("pointLights[" + i + "].base.color");
        	addUniform("pointLights[" + i + "].base.intensity");
        	addUniform("pointLights[" + i + "].atten.constant");
        	addUniform("pointLights[" + i + "].atten.linear");
        	addUniform("pointLights[" + i + "].atten.exponent");
        	addUniform("pointLights[" + i + "].position");
        }
    }

    public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material) {
        if(material.getTexture() != null) {
            material.getTexture().bind();
        } else {
            RenderUtil.unbindTextures();
        }

        setUniform("transform", worldMatrix);
        setUniform("transformProjected", projectedMatrix);
        setUniform("baseColor", material.getColor());

        setUniform("ambientLight", ambientLight);
        setUniform("directionalLight", directionalLight);

        for(int i = 0; i < pointLights.length; i++) {
            setUniform("pointLights[" + i + "]", pointLights[i]);
        }

        setUniformf("specularIntensity", material.getSpecularIntensity());
        setUniformf("specularPower", material.getSpecularPower());

        setUniform("eyePos", Transform.getCamera().getPos());

    }

    public void setUniform(String uniformName, BaseLight baseLight) {
        setUniform(uniformName + ".color", baseLight.getColor());
        setUniformf(uniformName + ".intensity", baseLight.getIntensity());
    }

    public void setUniform(String uniformName, DirectionalLight directionalLight) {
        setUniform(uniformName + ".base", directionalLight.getBaseLight());
        setUniform(uniformName + ".direction", directionalLight.getDirection());
    }

    public void setUniform(String uniformName, PointLight pointLight) {
        setUniform(uniformName + ".base", pointLight.getBase());
        setUniformf(uniformName + ".atten.constant", pointLight.getAtten().getConstant());
        setUniformf(uniformName + ".atten.linear", pointLight.getAtten().getLinear());
        setUniformf(uniformName + ".atten.exponent", pointLight.getAtten().getExponent());
        setUniform(uniformName + ".position", pointLight.getPosition());
    }

    public static void setPointLights(PointLight[] pointLights) {
        if(pointLights.length > MAX_POINT_LIGHTS) {
            System.err.println(pointLights.length + " point lights passed. Max allowed is: " + MAX_POINT_LIGHTS);
            new Exception().printStackTrace();
            System.exit(1);
        } else {
            PhongShader.pointLights = pointLights;
        }
    }

}
