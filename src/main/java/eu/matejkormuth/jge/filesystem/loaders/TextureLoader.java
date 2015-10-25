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
package eu.matejkormuth.jge.filesystem.loaders;

import eu.matejkormuth.jge.filesystem.ResourceLoader;
import eu.matejkormuth.jge.rendering.BufferUtil;
import eu.matejkormuth.jge.rendering.gl.api.Texture;
import eu.matejkormuth.jge.rendering.gl.enums.Format;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.nio.ByteBuffer;

@Slf4j
public class TextureLoader implements ResourceLoader<Texture> {

    /**
     * We load textures with RGBA format.
     */
    private static final Format PREFERRED_FORMAT = Format.RGBA;

    @Override
    public void loadInto(Texture resource, InputStream stream) throws Exception {
        // Decode image using awt.
        BufferedImage img = ImageIO.read(stream);

        int width = img.getWidth();
        int height = img.getHeight();

        int[] pixels = img.getRGB(0, 0, width, height, null, 0, width);

        // RGBA format
        ByteBuffer texData = BufferUtil.createByteBuffer(pixels.length * PREFERRED_FORMAT.getComponentCount());

        // Add alpha channel to pixel data and add all data to buffer.
        int currentPixel;
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                currentPixel = pixels[x * img.getWidth() + y];

                texData.put((byte) ((currentPixel >> 16) & 0xFF));
                texData.put((byte) ((currentPixel >> 8) & 0xFF));
                texData.put((byte) ((currentPixel) & 0xFF));

                if (img.getColorModel().hasAlpha()) {
                    texData.put((byte) ((currentPixel >> 24) & 0xFF));
                } else {
                    texData.put((byte) 0xFF);
                }
            }
        }

        img.flush();

        // Flip buffer.
        texData.flip();

        // Set texture format and upload data.
        resource.setFormat(PREFERRED_FORMAT);
        resource.setImageData(texData, width, height);
    }
}
