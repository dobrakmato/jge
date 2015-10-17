package eu.matejkormuth.jge.rendering.gl.lwjgl.gl21;

import eu.matejkormuth.jge.rendering.gl.enums.GLVersion;
import eu.matejkormuth.jge.rendering.gl.lwjgl.gl20.GL20Context;
import org.lwjgl.opengl.ContextAttribs;

public class GL21Context extends GL20Context {

    @Override
    protected ContextAttribs createContextAttributes() {
        return new ContextAttribs(2, 1);
    }

    @Override
    public GLVersion getGLVersion() {
        return GLVersion.GL21;
    }
}
