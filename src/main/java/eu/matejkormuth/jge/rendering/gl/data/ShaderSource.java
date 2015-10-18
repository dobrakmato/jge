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
package eu.matejkormuth.jge.rendering.gl.data;

import eu.matejkormuth.jge.exceptions.IllegalShaderTypeException;
import eu.matejkormuth.jge.rendering.gl.enums.ShaderType;
import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.TObjectIntMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import gnu.trove.map.hash.TObjectIntHashMap;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShaderSource {

    private static final char TOKEN_SYMBOL = '$';
    private static final String SHADER_TYPE_TOKEN = "type";
    private static final Pattern SHADER_TYPE_TOKEN_PATTERN = Pattern.compile("\\" + TOKEN_SYMBOL + SHADER_TYPE_TOKEN + " *: *(\\w+)");
    private static final String ATTRIBUTE_LAYOUT_TOKEN = "attrib_layout";
    private static final String TEXTURE_LAYOUT_TOKEN = "texture_layout";
    private static final Pattern LAYOUT_TOKEN_PATTERN = Pattern.compile("\\" + TOKEN_SYMBOL + "(" + ATTRIBUTE_LAYOUT_TOKEN + "|" + TEXTURE_LAYOUT_TOKEN + ") *: *(\\w+) *= *(\\d+)");

    /**
     * Type of the shader source.
     */
    private ShaderType type;

    /**
     * Layout of attributes in this shader.
     */
    private final TObjectIntMap<String> attributeLayouts = new TObjectIntHashMap<>();

    /**
     * Layout of texture samplers in this shader.
     */
    private final TIntObjectMap<String> textureLayouts = new TIntObjectHashMap<>();

    // Shader source.
    private String source;

    public ShaderSource(String source) {
        this.source = source;

        // Parse source.
        parseSource();
    }

    /**
     * Parses shader source and detects shader type and layouts.
     */
    private void parseSource() {
        for (String line : source.split("\\r?\\n")) {
            // Detect shader type.
            // Format: "$shader_type: fragment"
            Matcher matcher = SHADER_TYPE_TOKEN_PATTERN.matcher(line);
            while (matcher.find()) {
                try {
                    this.type = ShaderType.valueOf(matcher.group(1).toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new IllegalShaderTypeException(matcher.group(1));
                }
            }

            // Detect layouts.
            // Format: "// $texture_layout: diffuse = 0"
            // Format: "// $attrib_layout: position = 0"
            matcher = LAYOUT_TOKEN_PATTERN.matcher(line);
            while (matcher.find()) {
                final String token = matcher.group(1);
                switch (token) {
                    case ATTRIBUTE_LAYOUT_TOKEN:
                        attributeLayouts.put(matcher.group(2), Integer.parseInt(matcher.group(3)));
                        break;
                    case TEXTURE_LAYOUT_TOKEN:
                        textureLayouts.put(Integer.parseInt(matcher.group(3)), matcher.group(2));
                        break;
                }
            }
        }
    }

    public ShaderType getType() {
        return type;
    }

    public TIntObjectMap<String> getTextureLayouts() {
        return textureLayouts;
    }

    public TObjectIntMap<String> getAttributeLayouts() {
        return attributeLayouts;
    }

    public CharSequence getSource() {
        return source;
    }
}
