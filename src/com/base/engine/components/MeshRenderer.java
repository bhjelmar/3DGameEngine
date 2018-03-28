package com.base.engine.components;

import com.base.engine.core.Transform;
import com.base.engine.rendering.Material;
import com.base.engine.rendering.Mesh;
import com.base.engine.rendering.Shader;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MeshRenderer extends GameComponent {

	private Mesh mesh;
	private Material material;

	public void render(Shader shader) {
		shader.bind();
		shader.updateUniforms(getTransform(), material);
		mesh.draw();
	}

}
