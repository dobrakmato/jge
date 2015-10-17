package eu.matejkormuth.jge.rendering;

import java.nio.*;

public final class BufferUtil {

    private BufferUtil() {
    }

    public ByteBuffer createByteBuffer(int capacity) {
        return ByteBuffer.allocateDirect(capacity * Byte.BYTES).order(ByteOrder.nativeOrder());
    }

    public ShortBuffer createShortBuffer(int capacity) {
        return createByteBuffer(capacity * Short.BYTES).asShortBuffer();
    }

    public IntBuffer createIntBuffer(int capacity) {
        return createByteBuffer(capacity * Integer.BYTES).asIntBuffer();
    }

    public FloatBuffer createFloatBuffer(int capacity) {
        return createByteBuffer(capacity * Float.BYTES).asFloatBuffer();
    }

    public DoubleBuffer createDoubleBuffer(int capacity) {
        return createByteBuffer(capacity * Double.BYTES).asDoubleBuffer();
    }
}
