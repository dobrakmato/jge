package eu.matejkormuth.jge.rendering.gl.enums;

/**
 * Represents the different drawing modes for the vertex array
 */
public enum DrawingMode {
    POINTS(0x0), // GL11.GL_POINTS
    LINES(0x1), // GL11.GL_LINES
    LINE_LOOP(0x2), // GL11.GL_LINE_LOOP
    LINE_STRIP(0x3), // GL11.GL_LINE_STRIP
    TRIANGLES(0x4), // GL11.GL_TRIANGLES
    TRIANGLES_STRIP(0x5), // GL11.GL_TRIANGLE_STRIP
    TRIANGLE_FAN(0x7), // GL11.GL_TRIANGLE_FAN
    LINES_ADJACENCY(0xA), // GL32.GL_LINES_ADJACENCY
    LINE_STRIP_ADJACENCY(0xB), // GL32.GL_LINE_STRIP_ADJACENCY
    TRIANGLES_ADJACENCY(0xC), // GL32.GL_TRIANGLES_ADJACENCY
    TRIANGLE_STRIP_ADJACENCY(0xD), // GL32.GL_TRIANGLE_STRIP_ADJACENCY
    PATCHES(0xE); // GL40.GL_PATCHES

    private final int glConstant;

    DrawingMode(int constant) {
        glConstant = constant;
    }

    /**
     * Returns the OpenGL constant associated to the drawing mode
     *
     * @return The OpenGL constant
     */
    public int getGLConstant() {
        return glConstant;
    }
}
