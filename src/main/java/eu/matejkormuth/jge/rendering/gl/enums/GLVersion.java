package eu.matejkormuth.jge.rendering.gl.enums;

/**
 * An enum of the existing OpenGL versions. Use this class to generate rendering objects compatible with the version.
 */
public enum GLVersion {
    GL11(1, 1, false, 0, 0),
    GL12(1, 2, false, 0, 0),
    GL13(1, 3, false, 0, 0),
    GL14(1, 4, false, 0, 0),
    GL15(1, 5, false, 0, 0),
    GL20(2, 0, false, 1, 1),
    GL21(2, 1, false, 1, 2),
    GL30(3, 0, false, 1, 3),
    GL31(3, 1, false, 1, 4),
    GL32(3, 2, false, 1, 5),
    GL33(3, 3, false, 3, 3),
    GL40(4, 0, false, 4, 0),
    GL41(4, 1, false, 4, 1),
    GL42(4, 2, false, 4, 2),
    GL43(4, 3, false, 4, 3),
    GL44(4, 4, false, 4, 4),
    GLES10(1, 0, true, 1, 0),
    GLES11(1, 1, true, 1, 0),
    GLES20(2, 0, true, 1, 0),
    GLES30(3, 0, true, 3, 0),
    GLES31(3, 1, true, 3, 0),
    SOFTWARE(0, 0, false, 0, 0),
    OTHER(0, 0, false, 0, 0);

    private final int major;
    private final int minor;
    private final boolean es;
    private final int glslMajor;
    private final int glslMinor;

    GLVersion(int major, int minor, boolean es, int glslMajor, int glslMinor) {
        this.major = major;
        this.minor = minor;
        this.es = es;
        this.glslMajor = glslMajor;
        this.glslMinor = glslMinor;
    }

    /**
     * Returns the full version number of the version.
     *
     * @return The full version number
     */
    public int getFull() {
        return major * 10 + minor;
    }

    /**
     * Returns the major version number of the version.
     *
     * @return The major version number
     */
    public int getMajor() {
        return major;
    }

    /**
     * Returns the minor version number of the version.
     *
     * @return The minor version number
     */
    public int getMinor() {
        return minor;
    }

    /**
     * Returns true if the version is ES compatible, false if not.
     *
     * @return Whether or not this is an ES compatible version
     */
    public boolean isES() {
        return es;
    }

    /**
     * Returns the full GLSL version available with the OpenGL version.
     *
     * @return The GLSL version
     */
    public int getGLSLFull() {
        return glslMajor * 100 + glslMinor * 10;
    }

    /**
     * Returns the GLSL major version available with the OpenGL version. This version number is 0 if GLSL isn't supported.
     *
     * @return The GLSL major version, or 0 for unsupported
     */
    public int getGLSLMajor() {
        return glslMajor;
    }

    /**
     * Returns the GLSL minor version available with the OpenGL version.
     *
     * @return The GLSL minor version
     */
    public int getGLSLMinor() {
        return glslMinor;
    }

    /**
     * Returns true if this version supports GLSL, false if not.
     *
     * @return Whether or not this version supports GLSL
     */
    public boolean supportsGLSL() {
        return glslMajor != 0;
    }
}
