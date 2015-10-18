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

public enum Capability {
    BLEND(0xBE2), // GL11.GL_BLEND
    CULL_FACE(0xB44), // GL11.GL_CULL_FACE
    DEPTH_CLAMP(0x864F), // GL32.GL_DEPTH_CLAMP
    DEPTH_TEST(0xB71), // GL11.GL_DEPTH_TEST
    STENCIL_TEST(0xB90), // GL11.GL_STENCIL_TEST
    SCISSOR_TEST(0xC11); // GL11.GL_SCISSOR_TEST

    private final int glConstant;

    Capability(int glConstant) {
        this.glConstant = glConstant;
    }

    /**
     * Returns the OpenGL constant associated to the capability.
     *
     * @return The OpenGL constant
     */
    public int getGLConstant() {
        return glConstant;
    }
}
