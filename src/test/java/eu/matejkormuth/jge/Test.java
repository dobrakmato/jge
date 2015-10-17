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