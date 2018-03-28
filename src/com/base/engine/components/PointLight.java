package com.base.engine.components;

import com.base.engine.core.Vector3f;
import com.base.engine.rendering.ForwardPoint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PointLight extends BaseLight {

    private float range;
    private Vector3f attenuation;

    public PointLight(Vector3f color, float intensity, Vector3f attenuation) {
        super(color, intensity);
        this.attenuation = attenuation;
        this.range = 1000.0f; // TODO: calculate this

        setShader(ForwardPoint.getInstance());
    }

}
