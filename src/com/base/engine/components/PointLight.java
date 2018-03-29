package com.base.engine.components;

import com.base.engine.core.Vector3f;
import com.base.engine.rendering.ForwardPoint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PointLight extends BaseLight {

    private static final int COLOR_DEPTH = 256;

    private float range;
    private Vector3f attenuation;

    public PointLight(Vector3f color, float intensity, Vector3f attenuation) {
        super(color, intensity);
        this.attenuation = attenuation;

        float a = attenuation.getZ();
        float b = attenuation.getY();
        float c = attenuation.getX() - COLOR_DEPTH * getIntensity() * getColor().max();

        this.range = (float) (-b + Math.sqrt(Math.pow(b, 2) - 4 * a * c) / (2 * a));

        setShader(ForwardPoint.getInstance());
    }

}
