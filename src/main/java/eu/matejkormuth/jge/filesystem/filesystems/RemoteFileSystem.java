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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

public class RemoteFileSystem implements FileSystem {

    private static final String HTTP_HEAD = "HEAD";
    private static final String HTTP_GET = "GET";
    private static final String HTTP_POST = "POST";
    private static final String HTTP_PUT = "PUT";
    private static final String HTTP_DELETE = "DELETE";

    private static final String QUERY_PARAMS = "?action=$&param=@";

    private static final String REQUESTED_WITH = RemoteFileSystem.class.getSimpleName();

    private final String root;

    public RemoteFileSystem(String httpRoot) {
        this.root = httpRoot;

        // Check for validity.
        try {
            new URL(httpRoot);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private String getFullPath(String relative) {
        return root + relative;
    }

    @Override
    public boolean exists(String path) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(getFullPath(path)).openConnection();
            connection.setRequestProperty("X-Requested-With", REQUESTED_WITH);
            connection.setRequestMethod(HTTP_HEAD);
            return connection.getResponseCode() == HttpURLConnection.HTTP_OK;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    @Override
    public InputStream openRead(String path) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(getFullPath(path)).openConnection();
            connection.setRequestProperty("X-Requested-With", REQUESTED_WITH);
            connection.setRequestMethod(HTTP_GET);
            return connection.getInputStream();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
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
