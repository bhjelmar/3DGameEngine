package com.base.engine.core;

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

	public void input() {
		for(GameComponent component : components) {
			component.input(transform);
		}

		for(GameObject child : children) {
			child.input();
		}
	}

	public void update() {
		for(GameComponent component : components) {
			component.update(transform);
		}

		for(GameObject child : children) {
			child.update();
		}
	}

	public void render() {
		for(GameComponent component : components) {
			component.render(transform);
		}

		for(GameObject child : children) {
			child.render();
		}
	}

}
