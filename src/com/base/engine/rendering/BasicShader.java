package com.base.engine.rendering;

import com.base.engine.core.Matrix4f;
import lombok.Getter;

public class BasicShader extends Shader {

    @Getter
    private static final BasicShader instance = new BasicShader();

    private BasicShader() {
        super();

        addVertexShaderFromFile("basicVertex.vert");
        addFragmentShaderFromFile("basicFragment.frag");
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