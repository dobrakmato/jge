package eu.matejkormuth.jge.rendering.gl.enums;

/**
 * Represents a shader type.
 */
public enum ShaderType {
    FRAGMENT(0x8B30), // GL20.GL_FRAGMENT_SHADER
    VERTEX(0x8B31), // GL20.GL_VERTEX_SHADER
    GEOMETRY(0x8DD9), // GL32.GL_GEOMETRY_SHADER
    TESS_EVALUATION(0x8E87), // GL40.GL_TESS_EVALUATION_SHADER
    TESS_CONTROL(0x8E88), // GL40.GL_TESS_CONTROL_SHADER
    COMPUTE(0x91B9); // GL43.GL_COMPUTE_SHADER

    private final int glConstant;

    ShaderType(int glConstant) {
        this.glConstant = glConstant;
    }

    /**
     * Returns the OpenGL constant associated to the shader type.
     *
     * @return The OpenGL constant
     */
    public int getGLConstant() {
        return glConstant;
    }
}
