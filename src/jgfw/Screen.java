package jgfw;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by zeejfps on 11/26/16.
 */
public final class Screen {

    private final long window;
    private final int[] width, height;

    Screen(long window) {
        this.window = window;
        this.width = new int[1];
        this.height = new int[1];
    }

    void update() {
        glfwGetFramebufferSize(window, width, height);
    }

    public int width() {
        return width[0];
    }

    public int height() {
        return height[0];
    }

}
