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
import eu.matejkormuth.jge.rendering.gl.enums.ShaderType;
import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.TObjectIntMap;

public abstract class Shader implements Disposable {

    /**
     * Returns the shader type.
     *
     * @return shader type
     */
    public abstract ShaderType getType();

    /**
     * Compiles the shader.
     */
    public abstract void compile();

    /**
     * Returns the attribute layouts parsed from the tokens in the shader source.
     *
     * @return A map of the attribute name to the layout index.
     */
    public abstract TObjectIntMap<String> getAttributeLayouts();

    /**
     * Returns the texture layouts parsed from the tokens in the shader source.
     *
     * @return A map of the texture name to the layout index.
     */
    public abstract TIntObjectMap<String> getTextureLayouts();

    /**
     * Sets an attribute layout.
     *
     * @param attribute The name of the attribute
     * @param layout    The layout for the attribute
     */
    public abstract void setAttributeLayout(String attribute, int layout);

    /**
     * Sets a texture layout.
     *
     * @param unit    The unit for the sampler
     * @param sampler The sampler name
     */
    public abstract void setTextureLayout(int unit, String sampler);

}
