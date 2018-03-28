package com.base.engine.components;

import com.base.engine.core.GameObject;
import com.base.engine.core.RenderingEngine;
import com.base.engine.core.Transform;
import com.base.engine.rendering.Shader;
import lombok.Setter;

public abstract class GameComponent {

	@Setter
	private GameObject parent;

	public void input(float delta) {}
	public void update(float delta) {}
	public void render(Shader shader) {}

	public void addToRenderingEngine(RenderingEngine renderingEngine) {}

	public Transform getTransform() {
		return parent.getTransform();
	}

}
