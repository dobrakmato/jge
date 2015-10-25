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
package eu.matejkormuth.jge.configuration;

import eu.matejkormuth.jge.UnsafeUtils;

import javax.annotation.Nullable;
import java.io.*;
import java.util.Properties;

public class SimpleConfiguration extends Properties {

    public static SimpleConfiguration loadFrom(InputStream inputStream) {
        SimpleConfiguration configuration = new SimpleConfiguration();
        configuration.load(inputStream);
        return configuration;
    }

    public static SimpleConfiguration loadFrom(Reader inputStream) {
        SimpleConfiguration configuration = new SimpleConfiguration();
        configuration.load(inputStream);
        return configuration;
    }

    /*
     * Make these methods do not throw exception.
     */

    @Override
    public synchronized void load(InputStream inStream) {
        try {
            super.load(inStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void load(Reader reader) {
        try {
            super.load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void loadFromXML(InputStream in) {
        try {
            super.loadFromXML(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void store(OutputStream out, String comments) {
        try {
            super.store(out, comments);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void store(Writer writer, String comments) {
        try {
            super.store(writer, comments);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void storeToXML(OutputStream os, String comment) {
        try {
            super.storeToXML(os, comment);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void storeToXML(OutputStream os, String comment, String encoding) {
        try {
            super.storeToXML(os, comment, encoding);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * Define some useful methods to get primitive values from configuration in right type.
     */

    public boolean getBoolean(String key) {
        return Boolean.valueOf(UnsafeUtils.cast(get(key)));
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return containsKey(key) ? Boolean.valueOf(UnsafeUtils.cast(get(key))) : defaultValue;
    }

    public byte getByte(String key) {
        return Byte.valueOf(UnsafeUtils.cast(get(key)));
    }

    public byte getByte(String key, byte defaultValue) {
        return containsKey(key) ? Byte.valueOf(UnsafeUtils.cast(get(key))) : defaultValue;
    }

    public int getInteger(String key) {
        return Integer.valueOf(UnsafeUtils.cast(get(key)));
    }

    public int getInteger(String key, int defaultValue) {
        return containsKey(key) ? Integer.valueOf(UnsafeUtils.cast(get(key))) : defaultValue;
    }

    public long getLong(String key) {
        return Long.valueOf(UnsafeUtils.cast(get(key)));
    }

    public long getLong(String key, long defaultValue) {
        return containsKey(key) ? Long.valueOf(UnsafeUtils.cast(get(key))) : defaultValue;
    }

    public float getFloat(String key) {
        return Float.valueOf(UnsafeUtils.cast(get(key)));
    }

    public float getFloat(String key, float defaultValue) {
        return containsKey(key) ? Float.valueOf(UnsafeUtils.cast(get(key))) : defaultValue;
    }

    public double getDouble(String key) {
        return Double.valueOf(UnsafeUtils.cast(get(key)));
    }

    public double getDouble(String key, double defaultValue) {
        return containsKey(key) ? Double.valueOf(UnsafeUtils.cast(get(key))) : defaultValue;
    }

    public
    @Nullable
    String getString(String key) {
        return String.valueOf(UnsafeUtils.cast(get(key)));
    }

    public
    @Nullable
    String getString(String key, @Nullable String defaultValue) {
        return containsKey(key) ? String.valueOf(UnsafeUtils.cast(get(key))) : defaultValue;
    }

    /*
     * Maybe some more engine related types?
     */
}
