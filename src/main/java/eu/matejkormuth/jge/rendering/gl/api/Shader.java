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
