package com.base.engine.core;

import com.base.engine.rendering.Shader;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


public class GameObject {

	private List<GameObject> children;
	private List<GameComponent> components;
	@Getter
	private Transform transform;

	public GameObject() {
		children = new ArrayList<>();
		components = new ArrayList<>();
		transform = new Transform();
	}

	public void addChild(GameObject child) {
		children.add(child);
	}

	public void addComponent(GameComponent component) {
		components.add(component);
	}

	public void input(float delta) {
		for(GameComponent component : components) {
			component.input(transform, delta);
		}

		for(GameObject child : children) {
			child.input(delta);
		}
	}

	public void update(float delta) {
		for(GameComponent component : components) {
			component.update(transform, delta);
		}

		for(GameObject child : children) {
			child.update(delta);
		}
	}

	public void render(Shader shader) {
		for(GameComponent component : components) {
			component.render(transform, shader);
		}

		for(GameObject child : children) {
			child.render(shader);
		}
	}

}
