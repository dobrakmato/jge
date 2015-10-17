package eu.matejkormuth.jge.rendering.gl.enums;

/**
 * An enum of the possible frame buffer attachment points.
 */
public enum AttachmentPoint {
    COLOR0(0x8CE0, true), // GL30.GL_COLOR_ATTACHMENT0
    COLOR1(0x8CE1, true), // GL30.GL_COLOR_ATTACHMENT1
    COLOR2(0x8CE2, true), // GL30.GL_COLOR_ATTACHMENT2
    COLOR3(0x8CE3, true), // GL30.GL_COLOR_ATTACHMENT3
    COLOR4(0x8CE4, true), // GL30.GL_COLOR_ATTACHMENT4
    DEPTH(0x8D00, false), // GL30.GL_DEPTH_ATTACHMENT
    STENCIL(0x8D20, false), // GL30.GL_STENCIL_ATTACHMENT
    DEPTH_STENCIL(0x821A, false); // GL30.GL_DEPTH_STENCIL_ATTACHMENT

    private final int glConstant;
    private final boolean isColor;

    AttachmentPoint(int glConstant, boolean isColor) {
        this.glConstant = glConstant;
        this.isColor = isColor;
    }

    /**
     * Gets the OpenGL constant for this attachment point.
     *
     * @return The OpenGL Constant
     */
    public int getGLConstant() {
        return glConstant;
    }

    /**
     * Returns true if the attachment point is a color attachment.
     *
     * @return Whether or not the attachment is a color attachment
     */
    public boolean isColor() {
        return isColor;
    }
}
