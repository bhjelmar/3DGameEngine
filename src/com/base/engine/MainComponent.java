package com.base.engine;

import org.lwjgl.LWJGLException;

public class MainComponent {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;
	public static final String TITLE = "3D Engine";
	public static final double FRAME_CAP = 5000.0;

	private boolean isRunning;
	private Game game;

	public MainComponent() {
		System.out.println(RenderUtil.getOpenGlVersion());
		RenderUtil.initGraphics();
		isRunning = false;
		game = new Game();
	}

	public void start() {
		if(isRunning) {
			return;
		} else {
			run();
		}
	}

	public void stop() {
		isRunning = false;
	}

	private void run() {
		isRunning = true;

		int frames = 0;
		int frameCounter = 0;

		final double frameTime = 1.0 / FRAME_CAP;

		long lastTime = Time.getTime();
		double unprocessedTime = 0;

		while(isRunning) {

			boolean render = false;

			long startTime = Time.getTime();
			//passed time in 1 frame
			long passedTime = startTime - lastTime;
			lastTime = startTime;

			unprocessedTime += passedTime / (double) Time.SECOND;
			frameCounter += passedTime;

			while(unprocessedTime > frameTime) {
				render = true;

				unprocessedTime -= frameTime;

				if(Window.isCloseRequested()) {
					stop();
				}

				Time.setDelta(frameTime);
				game.input();
				Input.update();
				game.update();

				if(frameCounter >= Time.SECOND) {
					System.out.println("fps" + frames);
					frames = 0;
					frameCounter = 0;
				}

			}
			if(render) {
				render();
				frames++;
			} else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
		cleanup();
	}

	private void render() {
		RenderUtil.clearScreen();
		game.render();
		Window.render();
	}

	private void cleanup() {
		Window.dispose();
	}

	public static void main(String[] args) throws LWJGLException {
		Window.createWindow(WIDTH, HEIGHT, TITLE);
		MainComponent game = new MainComponent();
		game.start();
	}

}
