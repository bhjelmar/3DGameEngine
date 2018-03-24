package com.base.engine.rendering;

import com.base.engine.core.Vector3f;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class Material {

    private Texture texture;
    private Vector3f color;
    private float specularIntensity;
    private float specularPower;

    public Material(Texture texture)
	{
	    this(texture, new Vector3f(1,1,1));
	}

    public Material(Texture texture, Vector3f color)
    {
        this(texture, color, 2, 32);
    }

}
