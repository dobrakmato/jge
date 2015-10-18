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

import java.util.Collection;

public class HybridFileSystem implements FileSystem {

    private final LocalFileSystem localCache;
    private final RemoteFileSystem remote;

    public HybridFileSystem(String localRoot, String remoteRoot) {
        this.localCache = new LocalFileSystem(localRoot);
        this.remote = new RemoteFileSystem(remoteRoot);
    }

    private boolean localExists(Path path) {
        return localCache.exists(path);
    }

    @Override
    public Path get(String path) {
        return new Path(localCache.getRoot(), path);
    }

    @Override
    public boolean exists(Path path) {
        return localExists(path) || remote.exists(path);
    }

    @Override
    public boolean isFile(Path path) {
        return false;
    }

    @Override
    public boolean isDirectory(Path path) {
        return false;
    }

    @Override
    public Collection<Path> getAllFiles(Path directory) {
        return null;
    }
}
