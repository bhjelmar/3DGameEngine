package com.base.engine;

import lombok.Getter;
import lombok.Setter;

public class PhongShader extends Shader {

    @Getter
    private static final PhongShader instance = new PhongShader();

    @Getter @Setter
    private static Vector3f ambientLight;

    private PhongShader() {
        super();

        addVertexShader(ResourceLoader.loadShader("phongVertex.vert"));
        addFragmentShader(ResourceLoader.loadShader("phongFragment.frag"));
        compileShader();

        addUniform("transform");
        addUniform("baseColor");
        addUniform("ambientLight");
    }

    public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material) {
        if(material.getTexture() != null) {
            material.getTexture().bind();
        } else {
            RenderUtil.unbindTextures();
        }

        setUniform("transform", projectedMatrix);
        setUniform("baseColor", material.getColor());
        setUniform("ambientLight", ambientLight);
    }

}
