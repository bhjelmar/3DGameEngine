package com.base.engine.rendering;

import com.base.engine.core.Vector3f;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @AllArgsConstructor @ToString
public class PointLight {

    private BaseLight base;
    private Attenuation attenuation;
    private Vector3f position;
    private float range;

}
