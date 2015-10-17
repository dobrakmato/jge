package eu.matejkormuth.jge.rendering.gl.enums;

/**
 * An enum of the blending functions.
 */
public enum BlendFunction {
    GL_ZERO(0x0), // GL11.GL_ZERO
    GL_ONE(0x1), // GL11.GL_ONE
    GL_SRC_COLOR(0x300), // GL11.GL_SRC_COLOR
    GL_ONE_MINUS_SRC_COLOR(0x301), // GL11.GL_ONE_MINUS_SRC_COLOR
    GL_DST_COLOR(0x306), // GL11.GL_DST_COLOR
    GL_ONE_MINUS_DST_COLOR(0x307), // GL11.GL_ONE_MINUS_DST_COLOR
    GL_SRC_ALPHA(0x302), // GL11.GL_SRC_ALPHA
    GL_ONE_MINUS_SRC_ALPHA(0x303), // GL11.GL_ONE_MINUS_SRC_ALPHA
    GL_DST_ALPHA(0x304), // GL11.GL_DST_ALPHA
    GL_ONE_MINUS_DST_ALPHA(0x305), // GL11.GL_ONE_MINUS_DST_ALPHA
    GL_CONSTANT_COLOR(0x8001), // GL11.GL_CONSTANT_COLOR
    GL_ONE_MINUS_CONSTANT_COLOR(0x8002), // GL11.GL_ONE_MINUS_CONSTANT_COLOR
    GL_CONSTANT_ALPHA(0x8003), // GL11.GL_CONSTANT_ALPHA
    GL_ONE_MINUS_CONSTANT_ALPHA(0x8004), // GL11.GL_ONE_MINUS_CONSTANT_ALPHA
    GL_SRC_ALPHA_SATURATE(0x308), // GL11.GL_SRC_ALPHA_SATURATE
    GL_SRC1_COLOR(0x88F9), // GL33.GL_SRC1_COLOR
    GL_ONE_MINUS_SRC1_COLOR(0x88FA), // GL33.GL_ONE_MINUS_SRC1_COLOR
    GL_SRC1_ALPHA(0x8589), // GL33.GL_SRC1_ALPHA
    GL_ONE_MINUS_SRC1_ALPHA(0x88FB); // GL33.GL_ONE_MINUS_SRC1_ALPHA

    private final int glConstant;

    BlendFunction(int glConstant) {
        this.glConstant = glConstant;
    }

    /**
     * Returns the OpenGL constant associated to the blending function.
     *
     * @return The OpenGL constant
     */
    public int getGLConstant() {
        return glConstant;
    }
}
