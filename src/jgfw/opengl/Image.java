package jgfw.opengl;

import jgfw.utils.IOUtils;
import org.lwjgl.stb.STBImage;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by zeejfps on 11/26/16.
 */
public class Image {

    private int width;
    private int height;
    private int components;
    private ByteBuffer pixels;

    public Image(int width, int height, int components, ByteBuffer pixels) {
        this.width = width;
        this.height = height;
        this.components = components;
        this.pixels = pixels;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public int components() {
        return components;
    }

    public ByteBuffer pixels() {
        return pixels;
    }

    public static Image load(String path) throws IOException {
        ByteBuffer img = IOUtils.resourceToByteBuffer(path);
        int[] w = new int[1];
        int[] h = new int[1];
        int[] c = new int[1];
        ByteBuffer pixels = STBImage.stbi_load_from_memory(img, w, h, c, 0);
        return new Image(w[0], h[0], c[0], pixels);
    }

}
