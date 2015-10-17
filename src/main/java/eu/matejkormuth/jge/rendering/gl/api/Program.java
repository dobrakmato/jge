package eu.matejkormuth.jge.rendering.gl.api;

import eu.matejkormuth.jge.Disposable;

public abstract class Program implements Disposable {

    public abstract void attach(Shader shader);

    public abstract void detach(Shader shader);

    public abstract void link();

    public abstract void use();


}
