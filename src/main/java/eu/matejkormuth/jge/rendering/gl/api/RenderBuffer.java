package eu.matejkormuth.jge.rendering.gl.api;

import eu.matejkormuth.jge.Disposable;
import eu.matejkormuth.jge.rendering.gl.enums.InternalFormat;

public abstract class RenderBuffer implements Disposable {

    /**
     * Sets the render buffer storage.
     *
     * @param format The format
     * @param width  The width
     * @param height The height
     */
    public abstract void setStorage(InternalFormat format, int width, int height);

    /**
     * Returns the render buffer format.
     *
     * @return The format
     */
    public abstract InternalFormat getFormat();

    /**
     * Returns the render buffer width.
     *
     * @return The width
     */
    public abstract int getWidth();

    /**
     * Returns the render buffer height.
     *
     * @return The height
     */
    public abstract int getHeight();

    /**
     * Binds the render buffer to the OpenGL context.
     */
    public abstract void bind();

    /**
     * Unbinds the render buffer from the OpenGL context.
     */
    public abstract void unbind();
}
