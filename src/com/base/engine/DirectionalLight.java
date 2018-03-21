package com.base.engine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DirectionalLight {

    private BaseLight baseLight;
    private Vector3f direction;

    public DirectionalLight(BaseLight baseLight, Vector3f direction) {
        this.baseLight = baseLight;
        this.direction = direction.normalized();
    }

}
