/*
 * Fragment shader.
 */

#version 400


// Incoming interpolated (between vertices) texture coordinates.
in vec2 interpolatedTexCoord;

// Uniform 2D sampler for our texture object.
uniform sampler2D texture0;
uniform vec4 baseColor;

// Outgoing final color.
out vec4 outputColor;

void main() {
    // We simply pad the interpolatedColor
    // outputColor = vec4(interpolatedColor, 1);
    // We sample texture0 at the interpolatedTexCoord
    outputColor = baseColor+texture(texture0, interpolatedTexCoord);
}
