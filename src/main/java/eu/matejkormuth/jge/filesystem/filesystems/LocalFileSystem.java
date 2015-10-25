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

import eu.matejkormuth.jge.filesystem.*;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

@Slf4j
public class LocalFileSystem implements FileSystem {

    private final String root;

    public LocalFileSystem(String root) {
        this.root = root;

        if (!Files.exists(Paths.get(root))) {
            log.error("Specified root path for LocalFileSystem was not found!");
        }
    }

    public String getRoot() {
        return root;
    }

    @Override
    public InputStream openRead(String path) {
        try {
            return new FileInputStream(Paths.get(root, path).toFile());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public OutputStream openWrite(String path) {
        try {
            return new FileOutputStream(Paths.get(root, path).toFile());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String readText(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(root, path)), Charset.forName("UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createDirectory(String path) throws IOException {
        Files.createDirectory(Paths.get(root, path));
    }

    @Override
    public void createDirectories(String path) throws IOException {
        Files.createDirectories(Paths.get(root, path));
    }

    @Override
    public void createFile(String path) throws IOException {
        Files.createFile(Paths.get(root, path));
    }

    @Override
    public boolean exists(String path) {
        return Files.exists(Paths.get(root, path));
    }

    @Override
    public boolean isDirectory(String path) {
        return Files.isDirectory(Paths.get(root, path));
    }

    @Override
    public Collection<String> getAllFiles(String directory) {
        return null;
    }

    @Override
    public boolean isFile(String path) {
        return Files.isRegularFile(Paths.get(root, path));
    }
}
