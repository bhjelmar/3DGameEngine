package com.base.engine.core;

import com.base.engine.components.GameComponent;
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
		component.setParent(this);
	}

	public void input(float delta) {
		for(GameComponent component : components) {
			component.input(delta);
		}

		for(GameObject child : children) {
			child.input(delta);
		}
	}

	public void update(float delta) {
		for(GameComponent component : components) {
			component.update(delta);
		}

		for(GameObject child : children) {
			child.update(delta);
		}
	}

	public void render(Shader shader) {
		for(GameComponent component : components) {
			component.render(shader);
		}

		for(GameObject child : children) {
			child.render(shader);
		}
	}

	public void addToRenderingEngine(RenderingEngine renderingEngine) {
		for(GameComponent component : components) {
			component.addToRenderingEngine(renderingEngine);
		}

		for(GameObject child : children) {
			child.addToRenderingEngine(renderingEngine);
		}
	}

}
