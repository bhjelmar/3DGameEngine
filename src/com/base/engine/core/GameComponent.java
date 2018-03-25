package com.base.engine.core;

import com.base.engine.rendering.Shader;

public interface GameComponent {

	void input(Transform transform, float delta);
	void update(Transform transform, float delta);
	void render(Transform transform, Shader shader);

}
