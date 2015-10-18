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
package eu.matejkormuth.jge.rendering.gl.api;

import eu.matejkormuth.jge.Disposable;
import eu.matejkormuth.jge.rendering.gl.enums.FilterMode;
import eu.matejkormuth.jge.rendering.gl.enums.Format;
import eu.matejkormuth.jge.rendering.gl.enums.InternalFormat;
import eu.matejkormuth.jge.rendering.gl.enums.WrapMode;

import java.nio.ByteBuffer;

public abstract class Texture implements Disposable {

    public abstract void bind(int samplerSlot);

    public abstract void unbind();

    /**
     * Sets the texture's format.
     *
     * @param format The format
     */
    public void setFormat(Format format) {
        setFormat(format, null);
    }

    /**
     * Sets the texture's format.
     *
     * @param format The format
     */
    public void setFormat(InternalFormat format) {
        setFormat(format.getFormat(), format);
    }

    /**
     * Sets the texture's format and internal format.
     *
     * @param format         The format
     * @param internalFormat The internal format
     */
    public abstract void setFormat(Format format, InternalFormat internalFormat);

    /**
     * Returns the texture's format
     *
     * @return the format
     */
    public abstract Format getFormat();

    /**
     * Returns the texture's internal format.
     *
     * @return The internal format
     */
    public abstract InternalFormat getInternalFormat();

    /**
     * Sets the value for anisotropic filtering. Must be greater than zero. Note that this is EXT based and might not be supported on all hardware.
     *
     * @param value The anisotropic filtering value
     */
    public abstract void setAnisotropicFiltering(float value);

    /**
     * Sets the horizontal and vertical texture wraps.
     *
     * @param horizontalWrap The horizontal wrap
     * @param verticalWrap   The vertical wrap
     */
    public abstract void setWraps(WrapMode horizontalWrap, WrapMode verticalWrap);

    /**
     * Sets the texture's min and mag filters. The mag filter cannot require mipmap generation.
     *
     * @param minFilter The min filter
     * @param magFilter The mag filter
     */
    public abstract void setFilters(FilterMode minFilter, FilterMode magFilter);

    /**
     * Sets the texture's image data.
     *
     * @param imageData The image data
     * @param width     The width of the image
     * @param height    the height of the image
     */
    public abstract void setImageData(ByteBuffer imageData, int width, int height);

    /**
     * Returns the image data in the internal format.
     *
     * @return The image data in the internal format.
     */
    public ByteBuffer getImageData() {
        return getImageData(getInternalFormat());
    }

    /**
     * Returns the image data in the desired format.
     *
     * @param format The format to return the data in
     * @return The image data in the desired format
     */
    public abstract ByteBuffer getImageData(InternalFormat format);

    /**
     * Returns the width of the image.
     *
     * @return The image width
     */
    public abstract int getWidth();

    /**
     * Returns the height of the image.
     *
     * @return The image height
     */
    public abstract int getHeight();
}
