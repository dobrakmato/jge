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
 * Represents an attribute data type.
 */
public enum DataType {
    BYTE(0x1400, 1, true, true), // GL11.GL_BYTE
    UNSIGNED_BYTE(0x1401, 1, true, false), // GL11.GL_UNSIGNED_BYTE
    SHORT(0x1402, 2, true, true), // GL11.GL_SHORT
    UNSIGNED_SHORT(0x1403, 2, true, false), // GL11.GL_UNSIGNED_SHORT
    INT(0x1404, 4, true, true), // GL11.GL_INT
    UNSIGNED_INT(0x1405, 4, true, false), // GL11.GL_UNSIGNED_INT
    HALF_FLOAT(0x140B, 2, false, true), // GL30.GL_HALF_FLOAT
    FLOAT(0x1406, 4, false, true), // GL11.GL_FLOAT
    DOUBLE(0x140A, 8, false, true); // GL11.GL_DOUBLE

    private final int glConstant;
    private final int byteSize;
    private final boolean integer;
    private final boolean signed;
    private final int multiplyShift;

    DataType(int glConstant, int byteSize, boolean integer, boolean signed) {
        this.glConstant = glConstant;
        this.byteSize = byteSize;
        this.integer = integer;
        this.signed = signed;
        multiplyShift = (int) (Math.log(byteSize) / Math.log(2));
    }

    /**
     * Returns the OpenGL constant for the data type.
     *
     * @return The OpenGL constant
     */
    public int getGLConstant() {
        return glConstant;
    }

    /**
     * Returns the size in bytes of the data type.
     *
     * @return The size in bytes
     */
    public int getByteSize() {
        return byteSize;
    }

    /**
     * Returns true if the data type is an integer number ({@link DataType#BYTE}, {@link DataType#SHORT} or {@link DataType#INT}).
     *
     * @return Whether or not the data type is an integer
     */
    public boolean isInteger() {
        return integer;
    }

    /**
     * Returns true if this data type supports signed numbers, false if not.
     *
     * @return Whether or not this data type supports signed numbers
     */
    public boolean isSigned() {
        return signed;
    }

    /**
     * Returns the shift amount equivalent to multiplying by the number of bytes in this data type.
     *
     * @return The shift amount corresponding to the multiplication by the byte size
     */
    public int getMultiplyShift() {
        return multiplyShift;
    }
}
