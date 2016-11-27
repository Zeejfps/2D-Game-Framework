package jgfw.enums;

import static org.lwjgl.glfw.GLFW.GLFW_CURSOR_DISABLED;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR_HIDDEN;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR_NORMAL;

/**
 * Created by zeejfps on 11/24/16.
 */
public enum Cursor {
    NORMAL(GLFW_CURSOR_NORMAL),
    HIDDEN(GLFW_CURSOR_HIDDEN),
    DISABLED(GLFW_CURSOR_DISABLED);

    public final int mode;

    private Cursor(int mode) {
        this.mode = mode;
    }
}