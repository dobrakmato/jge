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

import eu.matejkormuth.jge.exceptions.ShaderCompileException;
import eu.matejkormuth.jge.rendering.gl.api.Shader;
import eu.matejkormuth.jge.rendering.gl.data.ShaderSource;
import eu.matejkormuth.jge.rendering.gl.enums.GLVersion;
import eu.matejkormuth.jge.rendering.gl.enums.ShaderType;
import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.TObjectIntMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import gnu.trove.map.hash.TObjectIntHashMap;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import javax.annotation.Nonnull;

public class GL20Shader extends Shader {

    /**
     * Type of shader.
     */
    protected ShaderType type;

    /**
     * Map of the attribute names to their vao index (optional for GL30 as they can be defined in the shader instead).
     */
    protected final TObjectIntMap<String> attributeLayouts = new TObjectIntHashMap<>();

    /**
     * Map of the texture units to their names.
     */
    protected final TIntObjectMap<String> textureLayouts = new TIntObjectHashMap<>();

    // Id of GL object.
    protected int id;


    public GL20Shader() {
    }

    public void setSource(@Nonnull ShaderSource source) {
        // If we haven't created shader yet, or this source is of different type.
        if (id == 0 || source.getType() != this.type) {
            // Delete old shader.
            GL20.glDeleteShader(id);

            // Create new shader.
            id = GL20.glCreateShader(type.getGLConstant());

            this.type = source.getType();
        }

        // Upload shader source.
        GL20.glShaderSource(id, source.getSource());

        // Set attribute and texture layouts.
        attributeLayouts.clear();
        attributeLayouts.putAll(source.getAttributeLayouts());
        textureLayouts.clear();
        textureLayouts.putAll(source.getTextureLayouts());
    }

    @Override
    public ShaderType getType() {
        return type;
    }

    @Override
    public void compile() {
        GL20.glCompileShader(id);

        // Check program link status.
        if (GL20.glGetShaderi(id, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            int length = GL20.glGetShaderi(id, GL20.GL_INFO_LOG_LENGTH);
            throw new ShaderCompileException(GL20.glGetShaderInfoLog(id, length));
        }
    }

    @Override
    public TObjectIntMap<String> getAttributeLayouts() {
        return attributeLayouts;
    }

    @Override
    public TIntObjectMap<String> getTextureLayouts() {
        return textureLayouts;
    }

    @Override
    public void setAttributeLayout(@Nonnull String attribute, int layout) {
        attributeLayouts.put(attribute, layout);
    }

    @Override
    public void setTextureLayout(int unit, @Nonnull String sampler) {
        textureLayouts.put(unit, sampler);
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
        GL20.glDeleteShader(id);
        id = -1;
    }

    @Override
    public boolean isDisposed() {
        return id == -1;
    }
}
