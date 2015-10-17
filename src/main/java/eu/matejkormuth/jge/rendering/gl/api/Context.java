package eu.matejkormuth.jge.rendering.gl.api;

import eu.matejkormuth.jge.Disposable;
import eu.matejkormuth.jge.rendering.gl.enums.Capability;
import eu.matejkormuth.jge.rendering.gl.enums.GLVersion;

public abstract class Context implements Disposable {

    public abstract void create();

    public abstract GLVersion getGLVersion();

    public abstract FrameBuffer createFramebuffer();

    public abstract Program createProgram();

    public abstract Shader createShader();

    public abstract Texture createTexture();

    public abstract VertexArray createVertexArray();

    public abstract void enableCapability(Capability capability);

    public abstract void disableCapability(Capability capability);

}
