package eu.matejkormuth.jge.rendering.gl.lwjgl.gl20;

import eu.matejkormuth.jge.rendering.gl.api.*;
import eu.matejkormuth.jge.rendering.gl.enums.Capability;
import eu.matejkormuth.jge.rendering.gl.enums.GLVersion;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class GL20Context extends Context {

    @Override
    public void create() {
        try {
            Display.create(new PixelFormat(), createContextAttributes());
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
    public FrameBuffer createFramebuffer() {
        return null;
    }

    @Override
    public Program createProgram() {
        return null;
    }

    @Override
    public Shader createShader() {
        return null;
    }

    @Override
    public Texture createTexture() {
        return null;
    }

    @Override
    public VertexArray createVertexArray() {
        return null;
    }

    @Override
    public void enableCapability(Capability capability) {
        GL11.glEnable(capability.getGLConstant());
    }

    @Override
    public void disableCapability(Capability capability) {
        GL11.glDisable(capability.getGLConstant());
    }

    @Override
    public void dispose() {
        Display.destroy();
    }
}
