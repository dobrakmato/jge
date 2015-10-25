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

import eu.matejkormuth.jge.AbstractObject;
import eu.matejkormuth.jge.exceptions.NoSuchNodeException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SceneNode extends AbstractObject implements Node {

    private Node parent = null;
    private String name = null;

    private final List<Node> children = new ArrayList<>();

    protected Vector3f position = new Vector3f(0, 0, 0);
    protected Vector3f scale = new Vector3f(1, 1, 1);
    protected Vector3f rotation = new Vector3f(0, 0, 0);

    public SceneNode() {
    }

    @Override
    public void addChild(@Nonnull Node node) {
        children.add(node);
        node.setParent(this);
    }

    @Override
    public void removeChild(@Nonnull Node node) {
        node.setParent(null);
        children.remove(node);
    }

    @Override
    public Node getChild(@Nonnull String name) {
        for (Node node : children) {
            if (name.equals(node.getName())) {
                return node;
            }
        }
        throw new NoSuchNodeException(name);
    }

    @Override
    public boolean hasChild(@Nonnull String name) {
        for (Node node : children) {
            if (name.equals(node.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getChildrenCount() {
        return children.size();
    }

    @Override
    public Vector3f getPosition() {
        return position;
    }

    @Override
    public Vector3f getScale() {
        return scale;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    @Override
    public void setPosition(@Nonnull Vector3f position) {
        this.position = position;
    }

    @Override
    public void setRotation(@Nonnull Vector3f rotation) {
        this.rotation = rotation;
    }

    @Override
    public void setScale(@Nonnull Vector3f scale) {
        this.scale = scale;
    }

    @Override
    public List<Node> getChildren() {
        return Collections.unmodifiableList(this.children);
    }

    @Nullable
    @Override
    public Node getParent() {
        return parent;
    }

    @Override
    public void setParent(Node node) {
        this.parent = node;
    }

    @Nullable
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(@Nullable String name) {
        this.name = name;
    }

    @Override
    public void draw() {

    }
}
