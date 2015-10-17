package eu.matejkormuth.jge.rendering.gl.enums;

/**
 * The uploading mode. When uploading attribute data to OpenGL, integer
 * data can be either converted to float or not (the later is only possible
 * with version 3.0+). When converting to float, the data can be normalized
 * or not. By default, {@link UploadMode#TO_FLOAT} is used as it provides
 * the best compatibility.
 */
public enum UploadMode {
    TO_FLOAT,
    TO_FLOAT_NORMALIZE,
    /**
     * Only supported in OpenGL 3.0 and after.
     */
    KEEP_INT;

    /**
     * Returns true if this upload mode converts integer data to normalized floats.
     *
     * @return Whether or not this upload mode converts integer data to normalized floats
     */
    public boolean normalize() {
        return this == TO_FLOAT_NORMALIZE;
    }

    /**
     * Returns true if this upload mode converts the data to floats.
     *
     * @return Whether or not this upload mode converts the data to floats
     */
    public boolean toFloat() {
        return this == TO_FLOAT || this == TO_FLOAT_NORMALIZE;
    }
}
