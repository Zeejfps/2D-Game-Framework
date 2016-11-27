package jgfw;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * Created by zeejfps on 11/16/16.
 */
public final class Game {

    public final Screen screen;
    public final Input input;

    private long window;
    private boolean running;

    public Game(Config config) {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            throw new IllegalStateException("Failed to initialize glfw");
        }

        int width, height;
        long monitor = glfwGetPrimaryMonitor();
        GLFWVidMode vidMode = glfwGetVideoMode(monitor);

        if (config.fullscreen) {
            width = vidMode.width();
            height = vidMode.height();
            glfwWindowHint(GLFW_DECORATED, GLFW_FALSE);
        }
        else {
            monitor = NULL;
            width = config.windowWidth;
            height = config.windowHeight;
        }

        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        window = glfwCreateWindow(
                width,
                height,
                config.windowTitle,
                monitor,
                NULL
        );
        if (window == NULL) {
            throw new RuntimeException("Failed to create glfw window");
        }

        screen = new Screen(window);
        input = new Input(window);

        glfwSetInputMode(window, GLFW_CURSOR, config.inputMode);

        glfwSetWindowPos(
                window,
                (vidMode.width() - width)/2,
                (vidMode.height() - height)/2
        );

        glfwMakeContextCurrent(window);
        glfwSwapInterval(config.vSync ? 1 : 0);
        glfwShowWindow(window);
        GL.createCapabilities();
    }

    public void launch(Scene scene) {
        if (running) return;
        running = true;
        scene.load();
        scene.start(this);
        while(running && !glfwWindowShouldClose(window)){
            glfwPollEvents();
            scene.update(this);
            scene.render(this);
            glfwSwapBuffers(window);
        }
        scene.unload();
        glfwDestroyWindow(window);
        glfwTerminate();
    }

    public void exit() {
        running = false;
    }
}
