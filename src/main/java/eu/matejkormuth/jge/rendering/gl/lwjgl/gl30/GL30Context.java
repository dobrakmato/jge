package eu.matejkormuth.jge.rendering.gl.lwjgl.gl30;

import eu.matejkormuth.jge.rendering.gl.enums.GLVersion;
import eu.matejkormuth.jge.rendering.gl.lwjgl.gl21.GL21Context;
import org.lwjgl.opengl.ContextAttribs;

public class GL30Context extends GL21Context {

    @Override
    protected ContextAttribs createContextAttributes() {
        return new ContextAttribs(3, 0);
    }

    @Override
    public GLVersion getGLVersion() {
        return GLVersion.GL30;
    }
}
