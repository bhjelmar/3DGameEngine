package com.base.engine.rendering;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @AllArgsConstructor @ToString
public class Attenuation {

    private float constant;
    private float linear;
    private float exponent;

}
