package com.base.engine.rendering;

import com.base.engine.core.Vector3f;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @AllArgsConstructor @ToString
public class BaseLight {

    private Vector3f color;
    private float intensity;

}
