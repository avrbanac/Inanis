#version 400 core

in vec2 passTextureCoords;

out vec4 outColor;

uniform sampler2D textureSampler;

void main(void) {
    outColor = texture(textureSampler, passTextureCoords);
}