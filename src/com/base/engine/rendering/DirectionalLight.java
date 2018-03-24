package com.base.engine.rendering;

import com.base.engine.core.Vector3f;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class DirectionalLight {

    private BaseLight baseLight;
    private Vector3f direction;

    public DirectionalLight(BaseLight baseLight, Vector3f direction) {
        this.baseLight = baseLight;
        this.direction = direction.normalized();
    }

    public void setDirection(Vector3f direction) {
        this.direction = direction.normalized();
    }

}
