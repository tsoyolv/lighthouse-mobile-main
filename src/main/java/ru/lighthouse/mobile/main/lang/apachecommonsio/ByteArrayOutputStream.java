package ru.lighthouse.mobile.main.lang.apachecommonsio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Copied from org.apache.commons.io.ByteArrayOutputStream
 * To decrease docker image size
 */
//@ThreadSafe
public class ByteArrayOutputStream extends AbstractByteArrayOutputStream {

    /**
     * Creates a new byte array output stream. The buffer capacity is
     * initially {@value AbstractByteArrayOutputStream#DEFAULT_SIZE} bytes, though its size increases if necessary.
     */
    public ByteArrayOutputStream() {
        this(DEFAULT_SIZE);
    }

    /**
     * Creates a new byte array output stream, with a buffer capacity of
     * the specified size, in bytes.
     *
     * @param size the initial size
     * @throws IllegalArgumentException if size is negative
     */
    public ByteArrayOutputStream(final int size) {
        if (size < 0) {
            throw new IllegalArgumentException(
                    "Negative initial size: " + size);
        }
        synchronized (this) {
            needNewBuffer(size);
        }
    }

    @Override
    public void write(final byte[] b, final int off, final int len) {
        if ((off < 0)
                || (off > b.length)
                || (len < 0)
                || ((off + len) > b.length)
                || ((off + len) < 0)) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return;
        }
        synchronized (this) {
            writeImpl(b, off, len);
        }
    }

    @Override
    public synchronized void write(final int b) {
        writeImpl(b);
    }

    @Override
    public synchronized int size() {
        return count;
    }

    /**
     * @see java.io.ByteArrayOutputStream#reset()
     */
    @Override
    public synchronized void reset() {
        resetImpl();
    }

    @Override
    public synchronized void writeTo(final OutputStream out) throws IOException {
        writeToImpl(out);
    }

    @Override
    public InputStream toInputStream() {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public synchronized byte[] toByteArray() {
        return toByteArrayImpl();
    }
}