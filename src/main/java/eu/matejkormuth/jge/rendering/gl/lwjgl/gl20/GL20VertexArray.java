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
import eu.matejkormuth.jge.rendering.gl.api.VertexArray;
import eu.matejkormuth.jge.rendering.gl.enums.GLVersion;
import org.lwjgl.opengl.APPLEVertexArrayObject;
import org.lwjgl.opengl.ARBVertexArrayObject;

public class GL20VertexArray extends VertexArray {

    protected int id;

    public GL20VertexArray() {
        MemoryUtil.register(this);
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

        id = -1;

        MemoryUtil.unregister(this);
    }

    @Override
    public boolean isDisposed() {
        return id == -1;
    }

    private enum VertexArrayExtension {
        NONE,
        ARB,
        APPLE;

        private boolean has() {
            return this != NONE;
        }

        private int glGenVertexArrays() {
            switch (this) {
                case ARB:
                    return ARBVertexArrayObject.glGenVertexArrays();
                case APPLE:
                    return APPLEVertexArrayObject.glGenVertexArraysAPPLE();
                default:
                    return 0;
            }
        }

        private void glBindVertexArray(int array) {
            switch (this) {
                case ARB:
                    ARBVertexArrayObject.glBindVertexArray(array);
                    break;
                case APPLE:
                    APPLEVertexArrayObject.glBindVertexArrayAPPLE(array);
            }
        }

        private void glDeleteVertexArrays(int array) {
            switch (this) {
                case ARB:
                    ARBVertexArrayObject.glDeleteVertexArrays(array);
                    break;
                case APPLE:
                    APPLEVertexArrayObject.glDeleteVertexArraysAPPLE(array);
            }
        }
    }
}
