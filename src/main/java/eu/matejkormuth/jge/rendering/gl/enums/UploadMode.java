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
 * The uploading mode. When uploading attribute data to OpenGL, integer
 * data can be either converted to float or not (the later is only possible
 * with version 3.0+). When converting to float, the data can be normalized
 * or not. By default, {@link UploadMode#TO_FLOAT} is used as it provides
 * the best compatibility.
 */
public enum UploadMode {
    TO_FLOAT,
    TO_FLOAT_NORMALIZE,
    /**
     * Only supported in OpenGL 3.0 and after.
     */
    KEEP_INT;

    /**
     * Returns true if this upload mode converts integer data to normalized floats.
     *
     * @return Whether or not this upload mode converts integer data to normalized floats
     */
    public boolean normalize() {
        return this == TO_FLOAT_NORMALIZE;
    }

    /**
     * Returns true if this upload mode converts the data to floats.
     *
     * @return Whether or not this upload mode converts the data to floats
     */
    public boolean toFloat() {
        return this == TO_FLOAT || this == TO_FLOAT_NORMALIZE;
    }
}
