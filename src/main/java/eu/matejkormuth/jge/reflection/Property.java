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
package eu.matejkormuth.jge.reflection;

import eu.matejkormuth.jge.exceptions.reflection.GetterNotFoundException;
import eu.matejkormuth.jge.exceptions.reflection.SetterNotFoundException;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class Property {

    // Empty description string.
    private static final String EMPTY_DESCRIPTION = "";

    // Method formats.
    private static final String GETTER_FORMAT = "get$";
    private static final String GETTER_BOOLEAN_FORMAT = "is$";
    private static final String SETTER_FORMAT = "set$";

    // Enable type checks.
    private static final boolean targetTypeCheck = true;
    private static final boolean valueTypeCheck = true;

    private final String name;
    private final String description;

    private final Class<?> clazz;
    private final Class<?> propertyType;
    private final Method getter;
    private final Method setter;

    public Property(@Nonnull Class<?> clazz, @Nonnull String propertyName) {
        this(clazz, propertyName, EMPTY_DESCRIPTION);
    }

    public Property(@Nonnull Class<?> clazz, @Nonnull String propertyName, @Nonnull String description) {
        this.name = propertyName;
        this.description = description;
        this.clazz = clazz;

        // Find getter.
        Method possibleGetter;
        try {
            possibleGetter = clazz.getMethod(formatGetter(propertyName));
        } catch (NoSuchMethodException e) {
            // Try boolean getter.
            try {
                possibleGetter = clazz.getMethod(formatBooleanGetter(propertyName));
            } catch (NoSuchMethodException e1) {
                throw new GetterNotFoundException(propertyName + " in class " + clazz.getName());
            }
        }

        // Get field type from getter.
        this.propertyType = possibleGetter.getReturnType();

        // Find setter.
        Method possibleSetter;
        try {
            possibleSetter = clazz.getMethod(formatSetter(propertyName), this.propertyType);
        } catch (NoSuchMethodException e) {
            throw new SetterNotFoundException(propertyName + " in class " + clazz.getName());
        }

        // Set accessibility of getter and setter.
        possibleGetter.setAccessible(true);
        possibleSetter.setAccessible(true);

        // Set getter and setter fields.
        this.getter = possibleGetter;
        this.setter = possibleSetter;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Object get(@Nonnull Object target) {
        if (targetTypeCheck) {
            checkTargetType(target);
        }

        try {
            return getter.invoke(target);
        } catch (IllegalAccessException e) {
            // This will never happen.
            log.error("Can't access getter!", e);
            return null;
        } catch (InvocationTargetException e) {
            log.error("Can't invoke getter!", e);
            return null;
        }
    }

    public void set(@Nonnull Object target, @Nonnull Object value) {
        if (targetTypeCheck) {
            checkTargetType(target);
        }
        if (valueTypeCheck) {
            checkValueType(value);
        }

        try {
            setter.invoke(target, value);
        } catch (IllegalAccessException e) {
            // This will never happen.
            log.error("Can't access setter!", e);
        } catch (InvocationTargetException e) {
            log.error("Can't invoke setter!", e);
        }
    }


    private void checkTargetType(Object target) {
        if (!target.getClass().equals(clazz)) {
            throw new IllegalArgumentException("Invalid target: class check failed!");
        }
    }

    private void checkValueType(Object value) {
        if (!value.getClass().equals(propertyType)) {
            throw new IllegalArgumentException("Invalid value: class check failed!");
        }
    }

    private static String formatGetter(@Nonnull String propertyName) {
        return GETTER_FORMAT.replace("$", propertyName);
    }

    private static String formatBooleanGetter(@Nonnull String propertyName) {
        return GETTER_BOOLEAN_FORMAT.replace("$", propertyName);
    }

    private static String formatSetter(@Nonnull String propertyName) {
        return SETTER_FORMAT.replace("$", propertyName);
    }
}
