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
package eu.matejkormuth.jge;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractObject implements GameObject {

    protected World world;
    private final List<GameComponent> components = new ArrayList<>();

    @Override
    public void addComponent(@Nonnull GameComponent component) {
        components.add(component);
    }

    @Override
    public void removeComponent(@Nonnull GameComponent component) {
        components.remove(component);
    }

    @Nullable
    @Override
    public <T> T getComponent(@Nonnull Class<T> type) {
        for (int i = 0; i < components.size(); i++) {
            if (type.isAssignableFrom(components.get(i).getClass())) {
                return UnsafeUtils.cast(components.get(i));
            }
        }
        return null;
    }

    @Nullable
    @Override
    public <T> T getComponentExact(@Nonnull Class<T> type) {
        for (int i = 0; i < components.size(); i++) {
            if (components.get(i).getClass().equals(type)) {
                return UnsafeUtils.cast(components.get(i));
            }
        }
        return null;
    }

    @Override
    public boolean hasComponents() {
        return components.size() != 0;
    }

    @Override
    public List<GameComponent> getComponents() {
        return Collections.unmodifiableList(components);
    }

    @Override
    public void update(float deltaTime) {
        if (!components.isEmpty()) {
            int count = components.size();
            for (int i = 0; i < count; i++) {
                components.get(i).update(deltaTime);
            }
        }
    }

    public void setWorld(@Nonnull World world) {
        this.world = world;
    }

    @Override
    public World getWorld() {
        return world;
    }
}
