package eu.matejkormuth.jge.rendering.gl.enums;

public enum Format {
    RED(0x1903, 1, true, false, false, false, false, false), // GL11.GL_RED
    RGB(0x1907, 3, true, true, true, false, false, false), // GL11.GL_RGB
    RGBA(0x1908, 4, true, true, true, true, false, false), // GL11.GL_RGBA
    DEPTH(0x1902, 1, false, false, false, false, true, false), // GL11.GL_DEPTH_COMPONENT
    RG(0x8227, 2, true, true, false, false, false, false), // GL30.GL_RG
    DEPTH_STENCIL(0x84F9, 1, false, false, false, false, false, true); // GL30.GL_DEPTH_STENCIL

    private final int glConstant;
    private final int components;
    private final boolean hasRed;
    private final boolean hasGreen;
    private final boolean hasBlue;
    private final boolean hasAlpha;
    private final boolean hasDepth;
    private final boolean hasStencil;

    Format(int glConstant, int components, boolean hasRed, boolean hasGreen,
           boolean hasBlue, boolean hasAlpha, boolean hasDepth, boolean hasStencil) {
        this.glConstant = glConstant;
        this.components = components;
        this.hasRed = hasRed;
        this.hasGreen = hasGreen;
        this.hasBlue = hasBlue;
        this.hasAlpha = hasAlpha;
        this.hasDepth = hasDepth;
        this.hasStencil = hasStencil;
    }

    /**
     * Gets the OpenGL constant for this format.
     *
     * @return The OpenGL Constant
     */
    public int getGLConstant() {
        return glConstant;
    }

    /**
     * Returns the number of components in the format.
     *
     * @return The number of components
     */
    public int getComponentCount() {
        return components;
    }

    /**
     * Returns true if this format has a red component.
     *
     * @return True if a red component is present
     */
    public boolean hasRed() {
        return hasRed;
    }

    /**
     * Returns true if this format has a green component.
     *
     * @return True if a green component is present
     */
    public boolean hasGreen() {
        return hasGreen;
    }

    /**
     * Returns true if this format has a blue component.
     *
     * @return True if a blue component is present
     */
    public boolean hasBlue() {
        return hasBlue;
    }

    /**
     * Returns true if this format has an alpha component.
     *
     * @return True if an alpha component is present
     */
    public boolean hasAlpha() {
        return hasAlpha;
    }

    /**
     * Returns true if this format has a depth component.
     *
     * @return True if a depth component is present
     */
    public boolean hasDepth() {
        return hasDepth;
    }

    /**
     * Returns true if this format has a stencil component.
     *
     * @return True if a stencil component is present
     */
    public boolean hasStencil() {
        return hasStencil;
    }
}
