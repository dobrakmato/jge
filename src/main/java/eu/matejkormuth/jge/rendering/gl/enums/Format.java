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

public enum Format {
    RED(0x1903, 1, true, false, false, false, false, false), // GL11.GL_RED
    RGB(0x1907, 3, true, true, true, false, false, false), // GL11.GL_RGB
    RGBA(0x1908, 4, true, true, true, true, false, false), // GL11.GL_RGBA
    DEPTH(0x1902, 1, false, false, false, false, true, false), // GL11.GL_DEPTH_COMPONENT
    RG(0x8227, 2, true, true, false, false, false, false), // GL30.GL_RG
    DEPTH_STENCIL(0x84F9, 1, false, false, false, false, false, true); // GL30.GL_DEPTH_STENCIL

    private final int glConstant;
    private final int components;
    private final boolean hasRed;
    private final boolean hasGreen;
    private final boolean hasBlue;
    private final boolean hasAlpha;
    private final boolean hasDepth;
    private final boolean hasStencil;

    Format(int glConstant, int components, boolean hasRed, boolean hasGreen,
           boolean hasBlue, boolean hasAlpha, boolean hasDepth, boolean hasStencil) {
        this.glConstant = glConstant;
        this.components = components;
        this.hasRed = hasRed;
        this.hasGreen = hasGreen;
        this.hasBlue = hasBlue;
        this.hasAlpha = hasAlpha;
        this.hasDepth = hasDepth;
        this.hasStencil = hasStencil;
    }

    /**
     * Gets the OpenGL constant for this format.
     *
     * @return The OpenGL Constant
     */
    public int getGLConstant() {
        return glConstant;
    }

    /**
     * Returns the number of components in the format.
     *
     * @return The number of components
     */
    public int getComponentCount() {
        return components;
    }

    /**
     * Returns true if this format has a red component.
     *
     * @return True if a red component is present
     */
    public boolean hasRed() {
        return hasRed;
    }

    /**
     * Returns true if this format has a green component.
     *
     * @return True if a green component is present
     */
    public boolean hasGreen() {
        return hasGreen;
    }

    /**
     * Returns true if this format has a blue component.
     *
     * @return True if a blue component is present
     */
    public boolean hasBlue() {
        return hasBlue;
    }

    /**
     * Returns true if this format has an alpha component.
     *
     * @return True if an alpha component is present
     */
    public boolean hasAlpha() {
        return hasAlpha;
    }

    /**
     * Returns true if this format has a depth component.
     *
     * @return True if a depth component is present
     */
    public boolean hasDepth() {
        return hasDepth;
    }

    /**
     * Returns true if this format has a stencil component.
     *
     * @return True if a stencil component is present
     */
    public boolean hasStencil() {
        return hasStencil;
    }
}
