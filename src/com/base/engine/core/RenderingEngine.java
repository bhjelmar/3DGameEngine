package com.base.engine.core;

import com.base.engine.components.BaseLight;
import com.base.engine.components.DirectionalLight;
import com.base.engine.components.PointLight;
import com.base.engine.rendering.*;
import com.base.engine.rendering.Window;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL30.GL_FRAMEBUFFER_SRGB;
import static org.lwjgl.opengl.GL32.GL_DEPTH_CLAMP;

public class RenderingEngine {

	@Getter @Setter
	private Camera mainCamera;
	@Getter @Setter
	private Vector3f ambientLight;

//	private PointLight[] pointLightList;

//	"Permanent" Structures
	private List<DirectionalLight> directionalLights;
	private List<PointLight> pointLights;

//	"actual permanent" structures
	private List<BaseLight> lights;
	@Getter
	private BaseLight activeLight;

	public RenderingEngine() {
		lights = new ArrayList<>();

		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		glFrontFace(GL_CW);
		glCullFace(GL_BACK);
		glEnable(GL_CULL_FACE);
		glEnable(GL_DEPTH_TEST);

		glEnable(GL_DEPTH_CLAMP);
		glEnable(GL_FRAMEBUFFER_SRGB);

		mainCamera = new Camera((float) Math.toRadians(70.0f), (float) Window.getWidth() / (float) Window.getHeight(), 0.01f, 1000.0f);
		ambientLight = new Vector3f(0.02f, 0.02f, 0.02f);
//		activeDirectionalLight = new DirectionalLight(
//				new BaseLight(
//						new Vector3f(0, 0, 1),
//						0.4f),
//				new Vector3f(1, 1, 1)
//		);
//		directionalLight2 = new DirectionalLight(
//				new BaseLight(
//						new Vector3f(1, 0, 0),
//						0.4f),
//				new Vector3f(-1, 1, -1)
//		);
//		activePointLight = new PointLight(
//				new BaseLight(
//						new Vector3f(0, 0, 1),
//						0.4f),
//				new Attenuation(0, 0, 1),
//				new Vector3f(5, 0, 5),
//				100
//		);
//		spotLight = new SpotLight(new PointLight(
//				new BaseLight(
//						new Vector3f(0, 1, 1),
//						0.4f),
//				new Attenuation(0, 0, 1f),
//				new Vector3f(0, 0, 0),
//				100
//		), new Vector3f(1, 0, 0), 0.7f);
//
//		int lightFieldWidth = 5;
//		int lightFieldDepth = 5;
//
//		float lightFieldStartX = 0;
//		float lightFieldStartY = 0;
//		float lightFieldStepX = 7;
//		float lightFieldStepY = 7;
//
//		pointLightList = new PointLight[lightFieldWidth * lightFieldDepth];
//		for(int i = 0; i < lightFieldWidth; i++) {
//			for(int j = 0; j < lightFieldDepth; j++) {
//				pointLightList[i * lightFieldWidth + j] = new PointLight(
//						new BaseLight(
//								new Vector3f(0, 1, 0),
//								0.4f),
//						new Attenuation(0, 0, 1),
//						new Vector3f(lightFieldStartX + lightFieldStepX * i, 0, lightFieldStartY + lightFieldStepY * j),
//						100
//				);
//			}
//		}
//
	}

	public void input(float delta) {
		mainCamera.input(delta);
	}

	public void render(GameObject object) {
		clearScreen();

		lights.clear();
		object.addToRenderingEngine(this);

		Shader forwardAmbient = ForwardAmbient.getInstance();
		forwardAmbient.setRenderingEngine(this);

		object.render(forwardAmbient);

		glEnable(GL_BLEND);
		glBlendFunc(GL_ONE, GL_ONE);
		glDepthMask(false);
		glDepthFunc(GL_EQUAL);

		for(BaseLight light : lights) {
			light.getShader().setRenderingEngine(this);
			activeLight = light;
			object.render(light.getShader());
		}

		glDepthFunc(GL_LESS);
		glDepthMask(true);
		glDisable(GL_BLEND);
	}

	private static void clearScreen() {
//		TODO: stencil buffer
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

	private static void setTextures(boolean enabled) {
		if(enabled) {
			glEnable(GL_TEXTURE_2D);
		} else {
			glDisable(GL_TEXTURE_2D);
		}
	}

	private static void setClearColor(Vector3f color) {
		glClearColor(color.getX(), color.getY(), color.getZ(), 1.0f);
	}

	public static String getOpenGlVersion() {
		return glGetString(GL_VERSION);
	}

	private static void unbindTextures() {
		glBindTexture(GL_TEXTURE_2D, 0);
	}

	public void addLight(BaseLight light) {
		lights.add(light);
	}

//	public void addDirectionalLight(DirectionalLight directionalLight) {
//		directionalLights.add(directionalLight);
//	}
//
//	public void addPointLight(PointLight pointLight) {
//		pointLights.add(pointLight);
//	}

}
