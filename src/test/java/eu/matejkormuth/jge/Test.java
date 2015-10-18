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

import java.util.ArrayDeque;
import java.util.Deque;

public class Test {

    @org.junit.Test
    public void testTest() throws Exception {

        ImmutablePooled.pool = new ImmutablePooled.Pool(100);

        long start;

        long l = 0;
        for (int i = 0; i < 1000 * 1000 * 20; i++) {
            l += 1;
        }
        System.out.println(l);

        start = System.nanoTime();
        Immutable av2 = new Immutable(1, 1, 1);
        for (int i = 0; i < 1000 * 1000 * 100; i++) {
            av2 = av2.add(1, 1, 1);
        }
        System.out.println(System.nanoTime() - start);

        start = System.nanoTime();
        ImmutablePooled av3 = new ImmutablePooled(1, 1, 1);
        for (int i = 0; i < 1000 * 1000 * 100; i++) {
            av3 = av3.add(1, 1, 1);
        }
        System.out.println(System.nanoTime() - start);

        start = System.nanoTime();
        Mutable fv3 = new Mutable();
        for (int i = 0; i < 1000 * 1000 * 100; i++) {
            fv3.add(1, 1, 1);
        }
        System.out.println(System.nanoTime() - start);
    }

    static final class Mutable {
        float x;
        float y;
        float z;

        public void add(float x, float y, float z) {
            this.x += x;
            this.y += y;
            this.z += z;
        }
    }

    static final class Immutable {
        float x;
        float y;
        float z;

        public Immutable(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public Immutable add(float x, float y, float z) {
            return new Immutable(this.x + x, this.y + y, this.z + z);
        }
    }

    static final class ImmutablePooled {
        static class Pool {

            private Deque<ImmutablePooled> unused;

            public Pool(int i) {
                unused = new ArrayDeque<>(i);
                for (int j = 0; j < i; j++) {
                    unused.add(new ImmutablePooled(0, 0, 0));
                }
            }

            public ImmutablePooled g() {
                return unused.getFirst();
            }

            public void r(ImmutablePooled o) {
                unused.add(o.reset());
            }
        }

        private ImmutablePooled reset() {
            this.x = this.y = this.z = 0;
            return this;
        }

        static Pool pool;

        float x;
        float y;
        float z;

        public ImmutablePooled(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public ImmutablePooled add(float x, float y, float z) {
            ImmutablePooled i = pool.g();
            i.x = this.x + x;
            i.y = this.y + y;
            i.z = this.z + z;
            pool.r(this);
            return i;
        }
    }
}