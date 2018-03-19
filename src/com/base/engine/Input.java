package com.base.engine;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.util.ArrayList;
import java.util.List;

public class Input {

	public static final int NUM_KEYCODES = 256;
	public static final int NUM_MOUSEBUTTONS = 25;

	private static List<Integer> currentKeys = new ArrayList<>();
	private static List<Integer> downKeys = new ArrayList<>();
	private static List<Integer> upKeys = new ArrayList<>();

	private static List<Integer> currentMouse = new ArrayList<>();
	private static List<Integer> downMouse = new ArrayList<>();
	private static List<Integer> upMouse = new ArrayList<>();

	public static void update() {
		upKeys.clear();
		for(int i = 0; i < NUM_KEYCODES; i++) {
			if(!getKey(i) && currentKeys.contains(i)) {
				upKeys.add(i);
			}
		}
		downKeys.clear();
		for(int i = 0; i < NUM_KEYCODES; i++) {
			if(getKey(i) && !currentKeys.contains(i)) {
				downKeys.add(i);
			}
		}
		currentKeys.clear();
		for(int i = 0; i < NUM_KEYCODES; i++) {
			 if(getKey(i)) {
			 	currentKeys.add(i);
			 }
		}

		upMouse.clear();
		for(int i = 0; i < NUM_MOUSEBUTTONS; i++) {
			if(!getMouse(i) && currentMouse.contains(i)) {
				upMouse.add(i);
			}
		}
		downMouse.clear();
		for(int i = 0; i < NUM_MOUSEBUTTONS; i++) {
			if(getMouse(i) && !currentMouse.contains(i)) {
				downMouse.add(i);
			}
		}
		currentMouse.clear();
		for(int i = 0; i < NUM_MOUSEBUTTONS; i++) {
			if(getMouse(i)) {
				currentMouse.add(i);
			}
		}
	}

	//Is key currently pressed
	public static boolean getKey(int keyCode) {
		return Keyboard.isKeyDown(keyCode);
	}

	//Was key pressed last frame
	public static boolean getKeyDown(int keyCode) {
		return downKeys.contains(keyCode);
	}

	public static boolean getKeyUp(int keyCode) {
		return upKeys.contains(keyCode);
	}

	public static boolean getMouse(int mouseButton) {
		return Mouse.isButtonDown(mouseButton);
	}

	public static boolean getMouseDown(int mouseButton) {
		return downMouse.contains(mouseButton);
	}

	public static boolean getMouseUp(int mouseButton) {
		return upMouse.contains(mouseButton);
	}

	public static Vector2f getMousePosition() {
		return new Vector2f(Mouse.getX(), Mouse.getY());
	}

}
