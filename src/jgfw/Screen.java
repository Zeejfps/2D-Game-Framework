package jgfw;

import org.lwjgl.opengl.GL11;

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
        glfwSetWindowSizeCallback(window, (l, w, h) -> {
            GL11.glViewport(0, 0, w, h);
        });
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
