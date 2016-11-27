package jgfw;

import jgfw.enums.Key;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by zeejfps on 11/24/16.
 */
public class Input {

    private final long window;

    private final double[] xPos, yPos;

    protected Input(long window) {
        this.window = window;
        this.xPos = new double[1];
        this.yPos = new double[1];
    }

    public boolean getKey(Key key) {
        return glfwGetKey(window, key.scancode) == GLFW_PRESS;
    }

    public double getMouseX() {
        glfwGetCursorPos(window, xPos, yPos);
        return xPos[0];
    }

    public double getMouseY() {
        glfwGetCursorPos(window, xPos, yPos);
        return yPos[0];
    }

}
