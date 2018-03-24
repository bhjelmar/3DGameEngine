package com.base.engine.rendering;

import com.base.engine.core.Vector3f;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class SpotLight {

    PointLight pointLight;
    Vector3f direction;
    float cutoff;

    public SpotLight(PointLight pointLight, Vector3f direction, float cutoff) {
        this.pointLight = pointLight;
        this.direction = direction.normalized();
        this.cutoff = cutoff;
    }

    public void setDirection(Vector3f direction) {
        this.direction = direction.normalized();
    }

}
