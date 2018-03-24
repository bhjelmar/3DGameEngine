package com.base.game;

import com.base.engine.core.GameComponent;
import com.base.engine.core.Transform;
import com.base.engine.rendering.BasicShader;
import com.base.engine.rendering.Material;
import com.base.engine.rendering.Mesh;
import com.base.engine.rendering.Shader;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MeshRenderer implements GameComponent {

	private Mesh mesh;
	private Material material;

	@Override
	public void input(Transform transform) {}

	@Override
	public void update(Transform transform) {}

	public void render(Transform transform) {
		Shader shader = BasicShader.getInstance();

		shader.bind();
		shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(), material);
		mesh.draw();
	}

}
