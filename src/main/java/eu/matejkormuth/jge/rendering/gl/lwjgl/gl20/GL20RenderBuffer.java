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

import eu.matejkormuth.jge.MemoryUtil;
import eu.matejkormuth.jge.exceptions.gl.UnsupportedExtensionException;
import eu.matejkormuth.jge.rendering.gl.api.RenderBuffer;
import eu.matejkormuth.jge.rendering.gl.enums.GLVersion;
import eu.matejkormuth.jge.rendering.gl.enums.InternalFormat;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GLContext;

import javax.annotation.Nonnull;

public class GL20RenderBuffer extends RenderBuffer {

    protected InternalFormat internalFormat;

    protected int width;
    protected int height;

    protected int id;

    public GL20RenderBuffer() {
        if (!GLContext.getCapabilities().GL_EXT_framebuffer_object) {
            throw new UnsupportedExtensionException("GL_EXT_framebuffer_object");
        }

        // Create render buffer.
        id = EXTFramebufferObject.glGenRenderbuffersEXT();

        MemoryUtil.register(this);
    }

    @Override
    public void setStorage(@Nonnull InternalFormat internalFormat, int width, int height) {
        if (width <= 0) {
            throw new IllegalArgumentException("width must be greater then zero");
        }

        if (height <= 0) {
            throw new IllegalArgumentException("height must be grater then zero");
        }

        this.internalFormat = internalFormat;
        this.width = width;
        this.height = height;

        // Bind the buffer.
        this.bind();

        // Allocate space.
        EXTFramebufferObject.glRenderbufferStorageEXT(EXTFramebufferObject.GL_RENDERBUFFER_EXT,
                internalFormat.getGLConstant(), width, height);

        // Unbind the buffer.
        this.unbind();
    }

    @Override
    public InternalFormat getFormat() {
        return internalFormat;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void bind() {
        EXTFramebufferObject.glBindRenderbufferEXT(EXTFramebufferObject.GL_RENDERBUFFER_EXT, id);
    }

    @Override
    public void unbind() {
        EXTFramebufferObject.glBindRenderbufferEXT(EXTFramebufferObject.GL_RENDERBUFFER_EXT, 0);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void dispose() {
        EXTFramebufferObject.glDeleteRenderbuffersEXT(id);
        id = -1;

        MemoryUtil.unregister(this);
    }

    @Override
    public boolean isDisposed() {
        return id == -1;
    }

    @Override
    public GLVersion getVersion() {
        return GLVersion.GL20;
    }
}
