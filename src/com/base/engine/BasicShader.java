package com.base.engine;

import lombok.Getter;

public class BasicShader extends Shader {

    @Getter
    private static final BasicShader instance = new BasicShader();

    private BasicShader() {
        super();

        addVertexShader(ResourceLoader.loadShader("basicVertex.vert"));
        addFragmentShader(ResourceLoader.loadShader("basicFragment.frag"));
        compileShader();

        addUniform("transform");
        addUniform("color");
    }

    public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material) {
        if(material.getTexture() != null) {
            material.getTexture().bind();
        } else {
            RenderUtil.unbindTextures();
        }

        setUniform("transform", projectedMatrix);
        setUniform("color", material.getColor());
    }

}
