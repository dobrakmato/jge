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
package eu.matejkormuth.jge.filesystem.filesystems;

import eu.matejkormuth.jge.filesystem.FileSystem;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;

@Slf4j
public class HybridFileSystem implements FileSystem {

    private final LocalFileSystem localCache;
    private final RemoteFileSystem remote;

    public HybridFileSystem(String localRoot, String remoteRoot) {
        this.localCache = new LocalFileSystem(localRoot);
        this.remote = new RemoteFileSystem(remoteRoot);
    }

    private boolean localExists(String path) {
        return localCache.exists(path);
    }

    @Override
    public InputStream openRead(String path) {
        return null;
    }

    @Override
    public OutputStream openWrite(String path) {
        return null;
    }

    @Override
    public String readText(String path) {
        return null;
    }

    @Override
    public void createDirectory(String path) throws IOException {

    }

    @Override
    public void createDirectories(String path) throws IOException {

    }

    @Override
    public void createFile(String path) throws IOException {

    }

    @Override
    public boolean exists(String path) {
        if (localExists(path)) {
            return true;
        }

        // Cache result.
        if (remote.exists(path)) {
            try {
                localCache.createDirectories(path);
                localCache.createFile(path);
            } catch (Exception e) {
                log.error("Can't create local file {}! Exception: {}", path, e);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean isFile(String path) {
        return false;
    }

    @Override
    public boolean isDirectory(String path) {
        return false;
    }

    @Override
    public Collection<String> getAllFiles(String directory) {
        return null;
    }
}
