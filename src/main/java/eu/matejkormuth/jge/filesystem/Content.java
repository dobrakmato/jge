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
package eu.matejkormuth.jge.filesystem;

import eu.matejkormuth.jge.filesystem.loaders.TextureLoader;
import eu.matejkormuth.jge.rendering.gl.api.Texture;
import eu.matejkormuth.jge.rendering.gl.lwjgl.gl20.GL20Texture;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class Content {

    private final FileSystem fileSystem;
    private final Map<Class<?>, ResourceLoader> resourceLoaders = new HashMap<>();
    private final Map<Class<?>, Supplier<Resource>> resourceFactories = new HashMap<>();

    public Content(FileSystem fileSystem) {
        this.fileSystem = fileSystem;

        initDefault();
    }

    private void initDefault() {
        resourceFactories.put(Texture.class, GL20Texture::new);

        resourceLoaders.put(Texture.class, new TextureLoader());
    }

    public <T> T load(Class<T> type, String path) {
        if (!resourceLoaders.containsKey(type)) {
            throw new IllegalArgumentException("No resource loader was found for resource type: " + type.getName());
        }

        if (!resourceFactories.containsKey(type)) {
            throw new IllegalArgumentException("No resource factory was found for resource type: " + type.getName());
        }

        Resource resource = resourceFactories.get(type).get();
        try {
            resourceLoaders.get(type).loadInto(resource, fileSystem.openRead(path));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return cast(resource);
    }

    @SuppressWarnings("unchecked")
    private <T> T cast(Resource resource) {
        return (T) resource;
    }
}
