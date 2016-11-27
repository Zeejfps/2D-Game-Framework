package jgfw.utils;

import org.lwjgl.BufferUtils;

import java.io.*;
import java.nio.ByteBuffer;

/**
 * Created by zeejfps on 11/24/16.
 */
public final class IOUtils {

    private IOUtils(){}

    public static ByteBuffer resourceToByteBuffer(String resource) throws IOException {
        InputStream istream = IOUtils.class.getClassLoader().getResourceAsStream(resource);
        ByteArrayOutputStream ostream = new ByteArrayOutputStream();
        try {
            int b;
            while ((b = istream.read()) != -1) {
                ostream.write(b);
            }
            byte[] data = ostream.toByteArray();
            ByteBuffer buffer = BufferUtils.createByteBuffer(data.length);
            buffer.put(data);
            buffer.flip();
            return buffer;
        }
        finally {
            istream.close();
            ostream.close();
        }
    }

    public static String resourceToString(String resource) throws IOException {
        BufferedReader istream = new BufferedReader(
                new InputStreamReader(IOUtils.class.getClassLoader().getResourceAsStream(resource)));
        try {
            String line, str="";
            while ((line = istream.readLine()) != null) {
                str += line + "\n";
            }
            return str;
        }
        finally {
            istream.close();
        }
    }

}
