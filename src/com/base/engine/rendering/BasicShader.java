package com.base.engine.rendering;

import com.base.engine.core.Matrix4f;
import com.base.engine.core.Transform;
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

    public void updateUniforms(Transform transform, Material material) {
        Matrix4f worldMatrix = transform.getTransformation();
        Matrix4f projectedMatrix = getRenderingEngine().getMainCamera().getViewProjection().mul(worldMatrix);


        material.getTexture().bind();

        setUniform("transform", projectedMatrix);
        setUniform("color", material.getColor());
    }

}
