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
package eu.matejkormuth.jge.rendering.gl.enums;

/**
 * An enum of sized texture component formats.
 */
public enum InternalFormat {
    RGB8(0x8051, Format.RGB, DataType.UNSIGNED_BYTE), // GL11.GL_RGB8
    RGBA8(0x8058, Format.RGBA, DataType.UNSIGNED_BYTE), // GL11.GL_RGBA8
    RGB16(32852, Format.RGB, DataType.UNSIGNED_SHORT), // GL11.GL_RGB16
    RGBA16(0x805B, Format.RGBA, DataType.UNSIGNED_SHORT), // GL11.GL_RGBA16
    DEPTH_COMPONENT16(0x81A5, Format.DEPTH, DataType.UNSIGNED_SHORT), // GL14.GL_DEPTH_COMPONENT16
    DEPTH_COMPONENT24(0x81A6, Format.DEPTH, DataType.UNSIGNED_INT), // GL14.GL_DEPTH_COMPONENT24
    DEPTH_COMPONENT32(0x81A7, Format.DEPTH, DataType.UNSIGNED_INT), // GL14.GL_DEPTH_COMPONENT32
    R8(0x8229, Format.RED, DataType.UNSIGNED_BYTE), // GL30.GL_R8
    R16(0x822A, Format.RED, DataType.UNSIGNED_SHORT), // GL30.GL_R16
    RG8(0x822B, Format.RG, DataType.UNSIGNED_BYTE), // GL30.GL_RG8
    RG16(0x822C, Format.RG, DataType.UNSIGNED_SHORT), // GL30.GL_RG16
    R16F(0x822D, Format.RED, DataType.HALF_FLOAT), // GL30.GL_R16F
    R32F(0x822E, Format.RED, DataType.FLOAT), // GL30.GL_R32F
    RG16F(0x822F, Format.RG, DataType.HALF_FLOAT), // GL30.GL_RG16F
    RG32F(0x8230, Format.RGB, DataType.FLOAT), // GL30.GL_RG32F
    RGBA32F(0x8814, Format.RGBA, DataType.FLOAT), // GL30.GL_RGBA32F
    RGB32F(0x8815, Format.RGB, DataType.FLOAT), // GL30.GL_RGB32F
    RGBA16F(0x881A, Format.RGBA, DataType.HALF_FLOAT), // GL30.GL_RGBA16F
    RGB16F(0x881B, Format.RGB, DataType.HALF_FLOAT); // GL30.GL_RGB16F

    private final int glConstant;
    private final Format format;
    private final int bytes;
    private final DataType componentType;

    InternalFormat(int glConstant, Format format, DataType componentType) {
        this.glConstant = glConstant;
        this.format = format;
        this.componentType = componentType;
        bytes = format.getComponentCount() * componentType.getByteSize();
    }

    /**
     * Gets the OpenGL constant for this internal format.
     *
     * @return The OpenGL Constant
     */
    public int getGLConstant() {
        return glConstant;
    }

    /**
     * Returns the format associated to this internal format
     *
     * @return The associated format
     */
    public Format getFormat() {
        return format;
    }

    /**
     * Returns the number of components in the format.
     *
     * @return The number of components
     */
    public int getComponentCount() {
        return format.getComponentCount();
    }

    /**
     * Returns the data type of the components.
     *
     * @return The component type
     */
    public DataType getComponentType() {
        return componentType;
    }

    /**
     * Returns the number of bytes used by a single pixel in the format.
     *
     * @return The number of bytes for a pixel
     */
    public int getBytes() {
        return bytes;
    }

    /**
     * Returns the number of bytes used by a single pixel component in the format.
     *
     * @return The number of bytes for a pixel component
     */
    public int getBytesPerComponent() {
        return componentType.getByteSize();
    }

    /**
     * Returns true if this format has a red component.
     *
     * @return True if a red component is present
     */
    public boolean hasRed() {
        return format.hasRed();
    }

    /**
     * Returns true if this format has a green component.
     *
     * @return True if a green component is present
     */
    public boolean hasGreen() {
        return format.hasGreen();
    }

    /**
     * Returns true if this format has a blue component.
     *
     * @return True if a blue component is present
     */
    public boolean hasBlue() {
        return format.hasBlue();
    }

    /**
     * Returns true if this format has an alpha component.
     *
     * @return True if an alpha component is present
     */
    public boolean hasAlpha() {
        return format.hasAlpha();
    }

    /**
     * Returns true if this format has a depth component.
     *
     * @return True if a depth component is present
     */
    public boolean hasDepth() {
        return format.hasDepth();
    }
}