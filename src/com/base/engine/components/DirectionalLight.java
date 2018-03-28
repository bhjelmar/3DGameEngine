package com.base.engine.components;

import com.base.engine.core.RenderingEngine;
import com.base.engine.core.Vector3f;
import com.base.engine.rendering.ForwardDirectional;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class DirectionalLight extends BaseLight {

    private Vector3f direction;

    public DirectionalLight(Vector3f color, float intensity, Vector3f direction) {
        super(color, intensity);
        this.direction = direction.normalized();

        setShader(ForwardDirectional.getInstance());
    }

    public void setDirection(Vector3f direction) {
        this.direction = direction.normalized();
    }

}
