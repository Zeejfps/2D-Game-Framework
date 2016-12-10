package jgfw;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * Created by zeejfps on 11/16/16.
 */
public abstract class Game {

    private static final int MAX_FRAME_SKIP = 5;

    public final Screen screen;
    public final Input input;
    public final Time time;

    private long window;
    private double nsPerUpdate;
    private volatile boolean running;

    public Game(Config config) {
        nsPerUpdate = (double)Time.NS_PER_SEC / config.fixedUpdateInterval;
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
        GL.createCapabilities();
    }

    /**
     * Displays the window and begins execution of the game.
     */
    public final void launch() {

        if (running)
            return;

        running = true;
        glfwShowWindow(window);
        onLaunch();
        long startTime = System.nanoTime();
        long accumulator = 0;
        long elapsed;
        while(running && !glfwWindowShouldClose(window)){
            elapsed = System.nanoTime() - startTime;
            accumulator += elapsed;
            time.setDeltaTime((float)elapsed / Time.NS_PER_SEC);
            int framesSkipped = 0;
            startTime = System.nanoTime();
            input.update();
            screen.update();
            glfwPollEvents();
            while (accumulator > nsPerUpdate && framesSkipped < MAX_FRAME_SKIP) {
                onFixedUpdate();
                accumulator -= nsPerUpdate;
                framesSkipped ++;
            }
            onUpdate();
            onRender();
            glfwSwapBuffers(window);
        }
        onExit();
        glfwDestroyWindow(window);
        glfwTerminate();
    }

    /**
     * Sets the running flag to false which will terminate the execution of the game.
     */
    public final void exit() {
        running = false;
    }

    /**
     * This method is called after the launch method is called,
     * right before entering the main game loop and after the
     * window has been displayed.
     */
    protected abstract void onLaunch();

    /**
     * This method is called every frame before the onRender() method.
     */
    protected abstract void onUpdate();

    /**
     * This method will always be called at a specified frame rate.
     */
    protected abstract void onFixedUpdate();

    /**
     * This method is called every frame after the onUpdate() method.
     */
    protected abstract void onRender();

    /**
     * This method is called once the game has exited its main loop
     * and before the game fully terminates.
     */
    protected abstract void onExit();

}
