package jgfw;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by zeejfps on 11/26/16.
 */
public class Screen {

    private final long window;
    private final int[] width, height;

    protected Screen(long window) {
        this.window = window;
        this.width = new int[1];
        this.height = new int[1];
    }

    public int width() {
        glfwGetFramebufferSize(window, width, height);
        return width[0];
    }

    public int height() {
        glfwGetFramebufferSize(window, width, height);
        return height[0];
    }

}
