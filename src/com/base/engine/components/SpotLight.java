package com.base.engine.components;

import com.base.engine.components.PointLight;
import com.base.engine.core.Vector3f;
import com.base.engine.rendering.ForwardSpot;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class SpotLight extends PointLight {

    Vector3f direction;
    float cutoff;

    public SpotLight(Vector3f color, float intensity, Vector3f attenuation, Vector3f direction, float cutoff) {
        super(color, intensity, attenuation);
        this.direction = direction.normalized();
        this.cutoff = cutoff;

        setShader(ForwardSpot.getInstance());
    }

    public void setDirection(Vector3f direction) {
        this.direction = direction.normalized();
    }

}
