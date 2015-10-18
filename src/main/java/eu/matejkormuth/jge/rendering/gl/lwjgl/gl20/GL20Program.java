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

import eu.matejkormuth.jge.exceptions.ProgramLinkException;
import eu.matejkormuth.jge.rendering.gl.api.Program;
import eu.matejkormuth.jge.rendering.gl.api.Shader;
import eu.matejkormuth.jge.rendering.gl.enums.GLVersion;
import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.TObjectIntMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import gnu.trove.map.hash.TObjectIntHashMap;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class GL20Program extends Program {

    private static final Logger log = LoggerFactory.getLogger(GL20Program.class);

    /**
     * Set of all shaders in program.
     */
    private Set<Shader> shaders = new HashSet<>();

    /**
     * Location of all uniforms in program.
     */
    private TObjectIntMap<String> uniformLocations = new TObjectIntHashMap<>();

    /**
     * Map of the attribute names to their vao index (optional for GL30 as they can be defined in the shader instead)
     */
    private final TObjectIntMap<String> attributeLayouts = new TObjectIntHashMap<>();

    /**
     * Map of the texture units to their names
     */
    private final TIntObjectMap<String> textureLayouts = new TIntObjectHashMap<>();

    // Id of program.
    protected int id;

    public GL20Program() {
        id = GL20.glCreateProgram();
    }

    @Override
    public void attach(@Nonnull Shader shader) {
        GL20.glAttachShader(id, shader.getId());

        shaders.add(shader);

        // Add layouts.
        attributeLayouts.putAll(shader.getAttributeLayouts());
        textureLayouts.putAll(shader.getTextureLayouts());
    }

    @Override
    public void detach(@Nonnull Shader shader) {
        GL20.glDetachShader(id, shader.getId());

        shaders.remove(shader);

        // Remove layouts.
        shader.getAttributeLayouts().keySet().forEach(attributeLayouts::remove);
        Arrays.stream(shader.getTextureLayouts().keys()).forEach(textureLayouts::remove);
    }

    @Override
    public void link() {
        // Try to link program.
        GL20.glLinkProgram(id);

        // Check program link status.
        if (GL20.glGetProgrami(id, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {
            int length = GL20.glGetProgrami(id, GL20.GL_INFO_LOG_LENGTH);
            throw new ProgramLinkException(GL20.glGetProgramInfoLog(id, length));
        }

        // TODO: Only when debug.

        // Validate program.
        GL20.glValidateProgram(id);
        if (GL20.glGetProgrami(id, GL20.GL_VALIDATE_STATUS) == GL11.GL_FALSE) {
            int length = GL20.glGetProgrami(id, GL20.GL_INFO_LOG_LENGTH);
            String validationProblem = GL20.glGetProgramInfoLog(id, length);
            log.warn("Program {} validation error: {}", this, validationProblem.trim());
        }
    }

    @Override
    public void use() {
        GL20.glUseProgram(id);
    }

    // TODO: Cache uniform location.


    @Override
    public Set<Shader> getShaders() {
        return Collections.unmodifiableSet(this.shaders);
    }

    @Override
    public Set<String> getUniformNames() {
        return Collections.unmodifiableSet(this.uniformLocations.keySet());
    }

    @Override
    public void setUniform(String name, boolean b) {

    }

    @Override
    public void setUniform(String name, int i) {

    }

    @Override
    public void setUniform(String name, float f) {

    }

    @Override
    public void setUniform(String name, float[] fs) {

    }

    @Override
    public void setUniform(String name, Vector2f v) {

    }

    @Override
    public void setUniform(String name, Vector2f[] vs) {

    }

    @Override
    public void setUniform(String name, Vector3f v) {

    }

    @Override
    public void setUniform(String name, Vector3f[] vs) {

    }

    @Override
    public void setUniform(String name, Vector4f v) {

    }

    @Override
    public void setUniform(String name, Matrix2f m) {

    }

    @Override
    public void setUniform(String name, Matrix3f m) {

    }

    @Override
    public void setUniform(String name, Matrix4f m) {

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
        GL20.glDeleteProgram(id);

        shaders.clear();
        uniformLocations.clear();

        id = -1;
    }

    @Override
    public boolean isDisposed() {
        return id == -1;
    }
}
