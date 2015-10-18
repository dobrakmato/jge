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

import org.lwjgl.util.vector.*;

import javax.annotation.Nonnull;
import java.util.Set;

public abstract class Program implements GraphicResource {

    public abstract void attach(@Nonnull Shader shader);

    public abstract void detach(@Nonnull Shader shader);

    public abstract void link();

    public abstract void use();

    public abstract Set<Shader> getShaders();

    public abstract Set<String> getUniformNames();

    public abstract void setUniform(String name, boolean b);

    public abstract void setUniform(String name, int i);

    public abstract void setUniform(String name, float f);

    public abstract void setUniform(String name, float[] fs);

    public abstract void setUniform(String name, Vector2f v);

    public abstract void setUniform(String name, Vector2f[] vs);

    public abstract void setUniform(String name, Vector3f v);

    public abstract void setUniform(String name, Vector3f[] vs);

    public abstract void setUniform(String name, Vector4f v);

    public abstract void setUniform(String name, Matrix2f m);

    public abstract void setUniform(String name, Matrix3f m);

    public abstract void setUniform(String name, Matrix4f m);
}
