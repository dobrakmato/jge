/**
 * anothergltry - Another GL try
 * Copyright (c) 2015, Matej Kormuth <http://www.github.com/dobrakmato>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package eu.matejkormuth.jge.rendering.gl.lwjgl.gl20;

import eu.matejkormuth.jge.exceptions.UnsupportedExtensionException;
import eu.matejkormuth.jge.rendering.BufferUtil;
import eu.matejkormuth.jge.rendering.gl.api.FrameBuffer;
import eu.matejkormuth.jge.rendering.gl.api.RenderBuffer;
import eu.matejkormuth.jge.rendering.gl.api.Texture;
import eu.matejkormuth.jge.rendering.gl.enums.AttachmentPoint;
import eu.matejkormuth.jge.rendering.gl.enums.GLVersion;
import gnu.trove.set.TIntSet;
import gnu.trove.set.hash.TIntHashSet;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GLContext;

import javax.annotation.Nonnull;
import java.nio.IntBuffer;
import java.util.Arrays;

public class GL20FrameBuffer extends FrameBuffer {

    /**
     * Set of output buffers.
     */
    private final TIntSet outputBuffers = new TIntHashSet();

    protected int id;

    public GL20FrameBuffer() {
        if (!GLContext.getCapabilities().GL_EXT_framebuffer_object) {
            throw new UnsupportedExtensionException("GL_EXT_framebuffer_object");
        }

        // Create render buffer object.
        id = EXTFramebufferObject.glGenFramebuffersEXT();

        this.bind();

        // Disable input buffers
        GL11.glReadBuffer(GL11.GL_NONE);

        this.unbind();
    }

    @Override
    public void bind() {
        EXTFramebufferObject.glBindFramebufferEXT(EXTFramebufferObject.GL_FRAMEBUFFER_EXT, id);
    }

    @Override
    public void unbind() {
        EXTFramebufferObject.glBindFramebufferEXT(EXTFramebufferObject.GL_FRAMEBUFFER_EXT, 0);
    }

    @Override
    public boolean isComplete() {
        this.bind();

        // Get framebuffer status.
        int result = EXTFramebufferObject.glCheckFramebufferStatusEXT(EXTFramebufferObject.GL_FRAMEBUFFER_EXT);

        this.unbind();

        // Returns whether the status is complete.
        return result == EXTFramebufferObject.GL_FRAMEBUFFER_COMPLETE_EXT;
    }

    @Override
    public void attach(@Nonnull AttachmentPoint point, @Nonnull Texture texture) {
        this.bind();

        // Attach the texture to this framebuffer.
        EXTFramebufferObject.glFramebufferTexture2DEXT(EXTFramebufferObject.GL_FRAMEBUFFER_EXT,
                point.getGLConstant(), GL11.GL_TEXTURE_2D, texture.getId(), 0);

        // Add it to the color outputs if it's a color type.
        if (point.isColor()) {
            outputBuffers.add(point.getGLConstant());
        }

        // Update the list of output buffers.
        updateOutputBuffers();

        this.unbind();
    }

    @Override
    public void attach(@Nonnull AttachmentPoint point, @Nonnull RenderBuffer buffer) {
        this.bind();

        // Attach the render buffer to this framebuffer.
        EXTFramebufferObject.glFramebufferRenderbufferEXT(EXTFramebufferObject.GL_FRAMEBUFFER_EXT,
                point.getGLConstant(), EXTFramebufferObject.GL_RENDERBUFFER_EXT, buffer.getId());

        // Add it to the color outputs if it's a color type.
        if (point.isColor()) {
            outputBuffers.add(point.getGLConstant());
        }

        // Update the list of output buffers.
        updateOutputBuffers();

        this.unbind();
    }

    @Override
    public void detach(@Nonnull AttachmentPoint point) {
        this.bind();

        EXTFramebufferObject.glFramebufferRenderbufferEXT(EXTFramebufferObject.GL_FRAMEBUFFER_EXT,
                point.getGLConstant(), EXTFramebufferObject.GL_RENDERBUFFER_EXT, 0);

        // Remove it from the color outputs if it's a color type.
        if (point.isColor()) {
            outputBuffers.remove(point.getGLConstant());
        }
        // Update the list of output buffers.
        updateOutputBuffers();

        this.unbind();
    }

    private void updateOutputBuffers() {
        // Set the output to the proper buffers.
        if (outputBuffers.isEmpty()) {
            // No color to output
            GL20.glDrawBuffers(GL11.GL_NONE);
        } else {
            // Keep track of the buffers to output.
            final int[] outputBuffersArray = outputBuffers.toArray();
            // Sorting the array ensures that attachments are in order n, n + 1, n + 2...
            // This is important!
            Arrays.sort(outputBuffersArray);

            final IntBuffer buffer = BufferUtil.createIntBuffer(outputBuffers.size());
            buffer.put(outputBuffersArray);
            buffer.flip();

            GL20.glDrawBuffers(buffer);
        }
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public GLVersion getVersion() {
        return GLVersion.GL20;
    }

    @Override
    public void dispose() {
        // Delete the frame buffer object.
        EXTFramebufferObject.glDeleteFramebuffersEXT(id);

        id = -1;

        outputBuffers.clear();
    }

    @Override
    public boolean isDisposed() {
        return id == -1;
    }
}
