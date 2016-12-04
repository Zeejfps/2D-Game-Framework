package jgfw;

import jgfw.enums.Cursor;

/**
 * Created by zeejfps on 11/16/16.
 */
public final class Config {

    protected float fixedUpdateInterval = 30;
    protected boolean vSync = false, fullscreen = false;
    protected int windowWidth = 640, windowHeight = 480;
    protected String windowTitle = "Untitled Game";
    protected int inputMode = Cursor.NORMAL.mode;

    public void setWindowTitle(String title) {
        this.windowTitle = title;
    }

    public void setWindowSize(int width, int height) {
        this.windowWidth = width;
        this.windowHeight = height;
    }

    public void setFullscreen(boolean fullscreen) {
        this.fullscreen = fullscreen;
    }

    public void setCursorMode(Cursor cursorMode) {
        this.inputMode = cursorMode.mode;
    }

    public void enableVSync(boolean enable) {
        this.vSync = enable;
    }

    public void setFixedUpdateInterval(float interval) {
        this.fixedUpdateInterval = interval;
    }

}
