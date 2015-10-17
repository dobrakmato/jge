package eu.matejkormuth.jge.rendering.gl.enums;

public enum TextureType {
    TEXTURE_1D(0xDE0),
    TEXTURE_2D(0xDE1),
    TEXTURE_RECTANGLE(34037),
    TEXTURE_3D(32879),
    TEXTURE_BUFFER(35882),
    TEXTURE_CUBE_MAP(34067),
    TEXTURE_1D_ARRAY(35864),
    TEXTURE_2D_ARRAY(35866),
    TEXTURE_2D_MULTISAMPLE(37120);

    private final int glConstant;

    TextureType(int glConstant) {
        this.glConstant = glConstant;
    }

    /**
     * Returns the OpenGL constant associated to the texture type.
     *
     * @return The OpenGL constant
     */
    public int getGLConstant() {
        return glConstant;
    }
    }
