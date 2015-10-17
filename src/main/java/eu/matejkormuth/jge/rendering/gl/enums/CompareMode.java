package eu.matejkormuth.jge.rendering.gl.enums;

public enum CompareMode {
    LEQUAL(0x203), // GL11.GL_LEQUAL
    GEQUAL(0x206), // GL11.GL_GEQUAL
    LESS(0x201), // GL11.GL_LESS
    GREATER(0x204), // GL11.GL_GREATER
    EQUAL(0x202), // GL11.GL_EQUAL
    NOTEQUAL(0x205), // GL11.GL_NOTEQUAL
    ALWAYS(0x206), // GL11.GL_ALWAYS
    NEVER(0x200); // GL11.GL_NEVER

    private final int glConstant;

    CompareMode(int glConstant) {
        this.glConstant = glConstant;
    }

    /**
     * Gets the OpenGL constant for this texture filter.
     *
     * @return The OpenGL Constant
     */
    public int getGLConstant() {
        return glConstant;
    }
}
