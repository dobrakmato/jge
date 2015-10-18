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
 * An enum of the possible frame buffer attachment points.
 */
public enum AttachmentPoint {
    COLOR0(0x8CE0, true), // GL30.GL_COLOR_ATTACHMENT0
    COLOR1(0x8CE1, true), // GL30.GL_COLOR_ATTACHMENT1
    COLOR2(0x8CE2, true), // GL30.GL_COLOR_ATTACHMENT2
    COLOR3(0x8CE3, true), // GL30.GL_COLOR_ATTACHMENT3
    COLOR4(0x8CE4, true), // GL30.GL_COLOR_ATTACHMENT4
    DEPTH(0x8D00, false), // GL30.GL_DEPTH_ATTACHMENT
    STENCIL(0x8D20, false), // GL30.GL_STENCIL_ATTACHMENT
    DEPTH_STENCIL(0x821A, false); // GL30.GL_DEPTH_STENCIL_ATTACHMENT

    private final int glConstant;
    private final boolean isColor;

    AttachmentPoint(int glConstant, boolean isColor) {
        this.glConstant = glConstant;
        this.isColor = isColor;
    }

    /**
     * Gets the OpenGL constant for this attachment point.
     *
     * @return The OpenGL Constant
     */
    public int getGLConstant() {
        return glConstant;
    }

    /**
     * Returns true if the attachment point is a color attachment.
     *
     * @return Whether or not the attachment is a color attachment
     */
    public boolean isColor() {
        return isColor;
    }
}
