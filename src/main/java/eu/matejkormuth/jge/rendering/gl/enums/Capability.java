package eu.matejkormuth.jge.rendering.gl.enums;

public enum Capability {
    BLEND(0xBE2), // GL11.GL_BLEND
    CULL_FACE(0xB44), // GL11.GL_CULL_FACE
    DEPTH_CLAMP(0x864F), // GL32.GL_DEPTH_CLAMP
    DEPTH_TEST(0xB71), // GL11.GL_DEPTH_TEST
    STENCIL_TEST(0xB90), // GL11.GL_STENCIL_TEST
    SCISSOR_TEST(0xC11); // GL11.GL_SCISSOR_TEST

    private final int glConstant;

    Capability(int glConstant) {
        this.glConstant = glConstant;
    }

    /**
     * Returns the OpenGL constant associated to the capability.
     *
     * @return The OpenGL constant
     */
    public int getGLConstant() {
        return glConstant;
    }
}
