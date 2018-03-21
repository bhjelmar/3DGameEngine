#version 120

varying vec2 texCoord0;

uniform vec3 baseColor;
uniform vec3 ambientLight;
uniform sampler2D sampler;

struct BaseLight {
    vec3 color;
    float intensity;
};

struct DirectionalLight {
    BaseLight base;
    vec3 direction;
};

vec4 calcLight(BaseLight base, vec3 direction, vec3 normal) {
    float diffuseFactor = dot(-direction, normal);

    return vec4(0,0,0,0);
}

vec4 calcDirectionalLight(DirectionalLight directionalLight, vec3 normal) {
    return calcLight(directionalLight.base, directionalLight.direction, normal);
}

void main()
{
    vec4 totalLight = vec4(ambientLight, 1);
    vec4 color = vec4(baseColor, 1);
    vec4 textureColor = texture2D(sampler, texCoord0.xy);

    if(textureColor != vec4(0, 0, 0, 0)) {
        color *= textureColor;
    }

    gl_FragColor = color * totalLight;
}