package eu.matejkormuth.jge.rendering.gl.api;

import eu.matejkormuth.jge.Disposable;
import eu.matejkormuth.jge.rendering.gl.enums.AttachmentPoint;

public abstract class FrameBuffer implements Disposable {

    public abstract void bind();

    public abstract void unbind();

    public abstract boolean isComplete();

    /**
     * Attaches the texture to the frame buffer attachment point.
     *
     * @param point The attachment point
     * @param texture The texture to attach
     */
    public abstract void attach(AttachmentPoint point, Texture texture);

    /**
     * Attaches the render buffer to the attachment point
     *
     * @param point The attachment point
     * @param buffer The render buffer
     */
    public abstract void attach(AttachmentPoint point, RenderBuffer buffer);

    /**
     * Detaches the texture or render buffer from the attachment point
     *
     * @param point The attachment point
     */
    public abstract void detach(AttachmentPoint point);
}
