package jgfw;

import jgfw.enums.Key;
import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by zeejfps on 11/24/16.
 */
public final class Input {

    public static final int NUM_KEYS = 400;

    private final long window;

    private final double[] mouseX, mouseY;
    private final boolean[] currKeys = new boolean[NUM_KEYS];
    private final boolean[] prevKeys = new boolean[NUM_KEYS];

    Input(long window) {
        this.window = window;
        this.mouseX = new double[1];
        this.mouseY = new double[1];

        glfwSetKeyCallback(window, new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                switch (action) {
                    case GLFW_REPEAT:
                    case GLFW_PRESS:
                        currKeys[key] = true;
                        break;
                    case GLFW_RELEASE:
                        currKeys[key] = false;
                        break;
                }
            }
        });
    }

    void update() {
        System.arraycopy(currKeys, 0, prevKeys, 0, NUM_KEYS);
        glfwGetCursorPos(window, mouseX, mouseY);
    }

    public boolean isKeyDown(Key key) {
        return currKeys[key.scancode];
    }

    public boolean isKeyPressed(Key key) {
        return !prevKeys[key.scancode] && currKeys[key.scancode];
    }

    public boolean isKeyReleased(Key key) {
        return prevKeys[key.scancode] && !currKeys[key.scancode];
    }

    public double getMouseX() {
        return mouseX[0];
    }

    public double getMouseY() {
        return mouseY[0];
    }

}
