package com.base.engine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @AllArgsConstructor @ToString
public class PointLight {

    private BaseLight base;
    private Attenuation atten;
    private Vector3f position;

}
