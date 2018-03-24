package com.base.engine.core;

public interface GameComponent {

	void input(Transform transform);
	void update(Transform transform);
	void render(Transform transform);

}
