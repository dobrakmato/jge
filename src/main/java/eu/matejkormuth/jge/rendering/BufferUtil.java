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
package eu.matejkormuth.jge.rendering;

import java.nio.*;

public final class BufferUtil {

    private BufferUtil() {
    }

    public ByteBuffer createByteBuffer(int capacity) {
        return ByteBuffer.allocateDirect(capacity * Byte.BYTES).order(ByteOrder.nativeOrder());
    }

    public ShortBuffer createShortBuffer(int capacity) {
        return createByteBuffer(capacity * Short.BYTES).asShortBuffer();
    }

    public IntBuffer createIntBuffer(int capacity) {
        return createByteBuffer(capacity * Integer.BYTES).asIntBuffer();
    }

    public FloatBuffer createFloatBuffer(int capacity) {
        return createByteBuffer(capacity * Float.BYTES).asFloatBuffer();
    }

    public DoubleBuffer createDoubleBuffer(int capacity) {
        return createByteBuffer(capacity * Double.BYTES).asDoubleBuffer();
    }
}
