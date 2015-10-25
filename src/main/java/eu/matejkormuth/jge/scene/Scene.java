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
package eu.matejkormuth.jge.scene;

import eu.matejkormuth.jge.Drawable;
import eu.matejkormuth.jge.Updatable;
import eu.matejkormuth.jge.filesystem.Resource;
import eu.matejkormuth.jge.reflection.Property;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Scene implements Updatable, Drawable {

    private SceneNode rootNode;
    private boolean setUp = false;

    public Scene() {
        rootNode = new SceneNode(10);
        rootNode.setName("RootNode");
    }

    public void setUp() {

        // Find resource locations.
        ResourceLocator locator = new ResourceLocator();
        locator.visitAll(rootNode);

        // Load resources (maybe gradually from low to high quality?).

        // Inject loaded resources to injection points.

        // Resolve physics objects.

        // Resolve lights.

        setUp = true;
    }

    public boolean isSetUp() {
        return setUp;
    }

    @Override
    public void draw() {
        if (rootNode != null) {
            rootNode.draw();
        }
    }

    @Override
    public void update(float deltaTime) {
        if (rootNode != null) {
            rootNode.update(deltaTime);
        }
    }

    private static class ResourceLocator extends NodeTreeVisitor {

        private final Map<Property, Resource> resourceLocations = new HashMap<>();

        @Override
        public void visit(Node node) {
            Class<? extends Node> nodeClass = node.getClass();
            for (Field field : nodeClass.getFields()) {
                if (field.isAnnotationPresent(Resource.class)) {
                    // Add this field to resource locations.
                    resourceLocations.put(new Property(nodeClass, field.getName()),
                            field.getDeclaredAnnotation(Resource.class));
                }
            }
        }

        public Map<Property, Resource> getResourceLocations() {
            return resourceLocations;
        }
    }
}
