package com.base.engine;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.util.ArrayList;
import java.util.List;

public class Input {

	public static final int NUM_KEYCODES = 256;
	public static final int NUM_MOUSEBUTTONS = 25;

	private static Boolean[] lastKeys = new Boolean[NUM_KEYCODES];
	private static Boolean[] lastMouse = new Boolean[NUM_MOUSEBUTTONS];

	public static void update() {
		for(int i = 0; i < NUM_KEYCODES; i++) {
			lastKeys[i] = getKey(i);
		}
		for(int i = 0; i < NUM_MOUSEBUTTONS; i++) {
			lastMouse[i] = getMouse(i);
		}
	}

	//Is key currently pressed
	public static boolean getKey(int keyCode) {
		return Keyboard.isKeyDown(keyCode);
	}

	//Was key pressed last frame
	public static boolean getKeyDown(int keyCode) {
		return getKey(keyCode) && !lastKeys[keyCode];
	}

	public static boolean getKeyUp(int keyCode) {
		return getKey(keyCode) && lastKeys[keyCode];
	}

	public static boolean getMouse(int mouseButton) {
		return Mouse.isButtonDown(mouseButton);
	}

	public static boolean getMouseDown(int mouseButton) {
		return getMouse(mouseButton) && !lastMouse[mouseButton];
	}

	public static boolean getMouseUp(int mouseButton) {
		return getMouse(mouseButton) && lastMouse[mouseButton];
	}

	public static Vector2f getMousePosition() {
		return new Vector2f(Mouse.getX(), Mouse.getY());
	}

	public static void setMousePosition(Vector2f pos) {
		Mouse.setCursorPosition((int) pos.getX(), (int) pos.getY());
	}

	public static void setCursor(boolean enabled) {
		Mouse.setGrabbed(!enabled);
	}

}
