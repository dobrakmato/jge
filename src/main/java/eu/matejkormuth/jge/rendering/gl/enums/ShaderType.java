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
 * Represents a shader type.
 */
public enum ShaderType {
    FRAGMENT(0x8B30), // GL20.GL_FRAGMENT_SHADER
    VERTEX(0x8B31), // GL20.GL_VERTEX_SHADER
    GEOMETRY(0x8DD9), // GL32.GL_GEOMETRY_SHADER
    TESS_EVALUATION(0x8E87), // GL40.GL_TESS_EVALUATION_SHADER
    TESS_CONTROL(0x8E88), // GL40.GL_TESS_CONTROL_SHADER
    COMPUTE(0x91B9); // GL43.GL_COMPUTE_SHADER

    private final int glConstant;

    ShaderType(int glConstant) {
        this.glConstant = glConstant;
    }

    /**
     * Returns the OpenGL constant associated to the shader type.
     *
     * @return The OpenGL constant
     */
    public int getGLConstant() {
        return glConstant;
    }
}
