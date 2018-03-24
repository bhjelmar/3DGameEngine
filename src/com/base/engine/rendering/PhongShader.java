package com.base.engine.rendering;

import com.base.engine.core.Matrix4f;
import com.base.engine.core.Transform;
import com.base.engine.core.Vector3f;
import lombok.Getter;
import lombok.Setter;

public class PhongShader extends Shader {

    private static final int MAX_POINT_LIGHTS = 4;
    private static final int MAX_SPOT_LIGHTS = 4;

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
    @Getter
    private static SpotLight[] spotLights = new SpotLight[] {};

    private PhongShader() {
        super();

        addVertexShaderFromFile("phongVertex.vert");
        addFragmentShaderFromFile("phongFragment.frag");
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
            addUniform("pointLights[" + i + "].range");
        }

        for(int i = 0; i < MAX_SPOT_LIGHTS; i++) {
            addUniform("spotLights[" + i + "].pointLight.base.color");
            addUniform("spotLights[" + i + "].pointLight.base.intensity");
            addUniform("spotLights[" + i + "].pointLight.atten.constant");
            addUniform("spotLights[" + i + "].pointLight.atten.linear");
            addUniform("spotLights[" + i + "].pointLight.atten.exponent");
            addUniform("spotLights[" + i + "].pointLight.position");
            addUniform("spotLights[" + i + "].pointLight.range");
            addUniform("spotLights[" + i + "].direction");
            addUniform("spotLights[" + i + "].cutoff");
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
        for(int i = 0; i < spotLights.length; i++) {
            setUniform("spotLights[" + i + "]", spotLights[i]);
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
        setUniformf(uniformName + ".atten.constant", pointLight.getAttenuation().getConstant());
        setUniformf(uniformName + ".atten.linear", pointLight.getAttenuation().getLinear());
        setUniformf(uniformName + ".atten.exponent", pointLight.getAttenuation().getExponent());
        setUniform(uniformName + ".position", pointLight.getPosition());
        setUniformf(uniformName + ".range", pointLight.getRange());
    }

    public void setUniform(String uniformName, SpotLight spotLight) {
        setUniform(uniformName + ".pointLight", spotLight.getPointLight());
        setUniform(uniformName + ".direction", spotLight.getDirection());
        setUniformf(uniformName + ".cutoff", spotLight.getCutoff());
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

    public static void setSpotLights(SpotLight[] spotLights) {
        if(spotLights.length > MAX_SPOT_LIGHTS) {
            System.err.println(spotLights.length + " spotlights passed. Max allowed is: " + MAX_SPOT_LIGHTS);
            new Exception().printStackTrace();
            System.exit(1);
        } else {
            PhongShader.spotLights = spotLights;
        }
    }

}
