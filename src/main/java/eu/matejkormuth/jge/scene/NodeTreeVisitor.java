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

import lombok.extern.slf4j.Slf4j;

/**
 * Represents recursive node tree visitor abstract class.
 */
@Slf4j
public abstract class NodeTreeVisitor {

    /**
     * Max depth of recursive method.
     */
    private static final int MAX_DEPTH = 256;

    /**
     * Creates new recursive node tree visitor.
     */
    public NodeTreeVisitor() {
    }

    // User visit method.
    public abstract void visit(Node node);

    /**
     * Visits all nodes in node tree including root node.
     */
    public void visitAll(Node root) {
        visitNode(root, 1);
    }

    /**
     * Visits specified node and recursively all it's children nodes.
     *
     * @param node node to visit
     */
    private void visitNode(Node node, int currentDepth) {
        if(currentDepth > MAX_DEPTH) {
            log.warn("Node tree is too deep, returning without visiting some nodes!");
            return;
        }

        // Descent level down if needed.
        if (node.hasChildren()) {
            node.getChildren().forEach(n -> visitNode(n, currentDepth + 1));
        }

        // Call user visit method.
        visit(node);
    }

}
