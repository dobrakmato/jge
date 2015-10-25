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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Keeps track of all disposable objects and provides way to check
 * for memory leak by not disposing disposable objects.
 */
public final class MemoryUtil {

    /**
     * Holds references to all disposable resources and objects.
     */
    private static final Map<Class<? extends Disposable>, List<Disposable>> disposables = new HashMap<>();

    /**
     * Adds specified Disposable object to registered disposables.
     * This method should be called from disposables's create
     * method / constructor, so we can track if this disposable has been disposed or not.
     *
     * @param disposable disposable that is being created
     */
    public static void register(@Nonnull Disposable disposable) {
        getDisposableList(disposable).add(disposable);
    }

    /**
     * Removes specified Disposable object from registered disposables.
     * This method should be called from disposable's dispose method.
     *
     * @param disposable disposable that is being disposed
     */
    public static void unregister(@Nonnull Disposable disposable) {
        getDisposableList(disposable).remove(disposable);
    }

    // Returns list of specific type of disposables.
    private static List<Disposable> getDisposableList(@Nonnull Disposable disposable) {
        Class<? extends Disposable> clazz = disposable.getClass();
        return disposables.containsKey(clazz) ? disposables.get(clazz) : disposables.put(clazz, new ArrayList<>());
    }

    /**
     * Returns whether all disposable objects are disposed at this moment.
     *
     * @return true if there are no undisposed disposable objects
     */
    public static boolean allDisposed() {
        if (disposables.size() == 0) {
            return true;
        }

        for (List<Disposable> list : disposables.values()) {
            if (list.size() != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns list of all undisposed disposable objects at this time.
     *
     * @return list of all undisposed objects in memory
     */
    public static List<Disposable> undisposed() {
        List<Disposable> undisposed = new ArrayList<>();

        disposables.values()
                .stream()
                .filter(list -> list.size() != 0)
                .forEach(undisposed::addAll);

        return undisposed;
    }

    /**
     * Safely disposes all undisposed objects.
     */
    public static void disposeUndisposed() {
        disposables.values()
                .stream()
                .filter(list -> list.size() != 0)
                .forEach(disposables1 -> disposables1
                        .stream()
                        .forEach(Disposable::dispose));
    }

    /**
     * Clears internal map of undisposed objects.
     */
    public static void clear() {
        disposables.clear();
    }
}
