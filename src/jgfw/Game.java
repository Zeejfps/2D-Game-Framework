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

    private static final int MAX_FRAME_SKIP = 5;

    public final Screen screen;
    public final Input input;
    public final Time time;

    private long window;
    private boolean running;
    private double nsPerUpdate;

    public Game(Config config) {
        nsPerUpdate = (double)Time.NS_IN_SEC / config.fixedUpdateInterval;
        System.out.println(nsPerUpdate);
        time = new Time();
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
        long startTime = System.nanoTime();
        long accumulator = 0;
        while(running && !glfwWindowShouldClose(window)){
            long elapsed = System.nanoTime() - startTime;
            accumulator += elapsed;
            time.setDeltaTime((float)elapsed / Time.NS_IN_SEC);
            int framesSkipped = 0;
            startTime = System.nanoTime();
            glfwPollEvents();
            while (accumulator > nsPerUpdate && framesSkipped < MAX_FRAME_SKIP) {
                scene.fixedUpdate(this);
                accumulator -= nsPerUpdate;
                framesSkipped ++;
            }
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
