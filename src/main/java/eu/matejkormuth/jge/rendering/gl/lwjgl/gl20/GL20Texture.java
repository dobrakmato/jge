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
import eu.matejkormuth.jge.rendering.BufferUtil;
import eu.matejkormuth.jge.rendering.gl.api.Texture;
import eu.matejkormuth.jge.rendering.gl.enums.*;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;

public class GL20Texture extends Texture {

    protected final TextureType type = TextureType.TEXTURE_2D;
    protected Format format = Format.RGBA;
    protected InternalFormat internalFormat;

    protected int height;
    protected int width;

    protected int id;

    public GL20Texture() {
        id = GL11.glGenTextures();

        MemoryUtil.register(this);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void activate(int samplerSlot) {
        if (samplerSlot < 0) {
            throw new IllegalArgumentException("samplerSlot must be greater or equal to 0");
        }

        // Activate sampler slot.
        GL13.glActiveTexture(GL13.GL_TEXTURE0 + samplerSlot);
    }

    @Override
    public void bind() {
        GL11.glBindTexture(type.getGLConstant(), id);
    }

    @Override
    public void unbind() {
        GL11.glBindTexture(type.getGLConstant(), 0);
    }

    @Override
    public void setFormat(@Nonnull Format format, @Nonnull InternalFormat internalFormat) {
        this.format = format;
        this.internalFormat = internalFormat;
    }

    @Override
    public TextureType getType() {
        return type;
    }

    @Override
    public Format getFormat() {
        return format;
    }

    @Override
    public InternalFormat getInternalFormat() {
        return internalFormat;
    }

    @Override
    public void setAnisotropicFiltering(float value) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public void setWraps(@Nonnull WrapMode horizontalWrap, @Nonnull WrapMode verticalWrap) {
        // Bind the texture
        this.bind();

        // Set the vertical and horizontal texture wraps
        GL11.glTexParameteri(type.getGLConstant(), GL11.GL_TEXTURE_WRAP_S, horizontalWrap.getGLConstant());
        GL11.glTexParameteri(type.getGLConstant(), GL11.GL_TEXTURE_WRAP_T, verticalWrap.getGLConstant());

        // Unbind the texture
        this.unbind();
    }

    @Override
    public void setFilters(@Nonnull FilterMode minFilter, @Nonnull FilterMode magFilter) {
        if (magFilter.needsMipmaps()) {
            throw new IllegalArgumentException("Mag filter cannot require mipmaps");
        }

        // Bind the texture
        this.bind();

        // Set filters
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, minFilter.getGLConstant());
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, magFilter.getGLConstant());

        // Unbind the texture
        this.unbind();
    }

    @Override
    public void setImageData(@Nonnull ByteBuffer imageData, int width, int height) {
        if (width <= 0) {
            throw new IllegalArgumentException("width must be greater then zero");
        }

        if (height <= 0) {
            throw new IllegalArgumentException("height must be greater then zero");
        }

        int oldWidth = this.width;
        int oldHeight = this.height;

        this.width = width;
        this.height = height;

        this.bind();

        // Whether this texture uses internal format.
        boolean usesIF = this.internalFormat != null;

        // Use byte alignment
        GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);

        if (oldHeight == height && oldWidth == width) {
            // Upload texture.
            GL11.glTexSubImage2D(type.getGLConstant(), 0, 0, 0, width, height,
                    format.getGLConstant(),
                    usesIF ? internalFormat.getComponentType().getGLConstant() : DataType.UNSIGNED_BYTE.getGLConstant(),
                    imageData);
        } else {
            // Reallocate and upload.
            GL11.glTexImage2D(type.getGLConstant(), 0, usesIF ? internalFormat.getGLConstant() : format.getGLConstant(),
                    width, height, 0, format.getGLConstant(),
                    usesIF ? internalFormat.getComponentType().getGLConstant() : DataType.UNSIGNED_BYTE.getGLConstant(),
                    imageData);
        }

        this.unbind();
    }

    @Override
    public ByteBuffer getImageData(@Nullable InternalFormat format) {
        // Bind the texture
        this.bind();

        // Create the image buffer
        final boolean formatNotNull = format != null;
        final ByteBuffer imageData = BufferUtil.createByteBuffer(width * height *
                (formatNotNull ? format.getBytes() : this.format.getComponentCount() * DataType.UNSIGNED_BYTE.getByteSize()));

        // Use byte alignment
        GL11.glPixelStorei(GL11.GL_PACK_ALIGNMENT, 1);

        // Get the image data
        GL11.glGetTexImage(type.getGLConstant(), 0, formatNotNull ? format.getFormat().getGLConstant() : this.format.getGLConstant(),
                formatNotNull ? format.getComponentType().getGLConstant() : DataType.UNSIGNED_BYTE.getGLConstant(), imageData);

        // Unbind the texture
        this.unbind();

        return imageData;
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
    public void dispose() {
        super.dispose();

        GL11.glDeleteTextures(id);

        id = -1;

        MemoryUtil.unregister(this);
    }

    @Override
    public GLVersion getVersion() {
        return GLVersion.GL20;
    }
}
