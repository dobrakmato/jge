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

import eu.matejkormuth.jge.Disposable;
import eu.matejkormuth.jge.MemoryUtil;
import eu.matejkormuth.jge.rendering.gl.api.*;
import eu.matejkormuth.jge.rendering.gl.enums.Capability;
import eu.matejkormuth.jge.rendering.gl.enums.GLVersion;
import lombok.extern.slf4j.Slf4j;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

import javax.annotation.Nonnull;

@Slf4j
public class GL20Context extends Context implements Disposable {

    @Override
    public void create(int width, int height, boolean fullscreen) {
        try {
            log.info("Creating a GL20 ({}) context.", this.getGLVersion());

            Display.setDisplayMode(new DisplayMode(width, height));
            Display.setFullscreen(fullscreen);
            Display.create(new PixelFormat(), createContextAttributes());

            MemoryUtil.register(this);

            log.info("LWJGL Display ({}) created!", this.getGLVersion());
        } catch (LWJGLException ex) {
            throw new IllegalStateException("Unable to create OpenGL context", ex);
        }
    }

    protected ContextAttribs createContextAttributes() {
        return new ContextAttribs(2, 0);
    }

    @Override
    public GLVersion getGLVersion() {
        return GLVersion.GL20;
    }

    @Override
    public FrameBuffer createFrameBuffer() {
        return new GL20FrameBuffer();
    }

    @Override
    public RenderBuffer createRenderBuffer() {
        return new GL20RenderBuffer();
    }

    @Override
    public Program createProgram() {
        return new GL20Program();
    }

    @Override
    public Shader createShader() {
        return new GL20Shader();
    }

    @Override
    public Texture createTexture() {
        return new GL20Texture();
    }

    @Override
    public VertexArray createVertexArray() {
        return new GL20VertexArray();
    }

    @Override
    public void enableCapability(@Nonnull Capability capability) {
        GL11.glEnable(capability.getGLConstant());
    }

    @Override
    public void disableCapability(@Nonnull Capability capability) {
        GL11.glDisable(capability.getGLConstant());
    }

    @Override
    public void dispose() {
        Display.destroy();

        MemoryUtil.unregister(this);
    }

    @Override
    public boolean isDisposed() {
        return !Display.isCreated();
    }
}
