import jgfw.Config;
import jgfw.Game;
import jgfw.enums.Cursor;
import jgfw.enums.Key;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;

/**
 * Created by zeejfps on 11/16/16.
 */
public class TestGame extends Game {

    public TestGame(Config config) {
        super(config);
    }

    int fixedUpdates;
    long startTime;

    @Override
    protected void onLaunch() {
        System.out.println("Begin");
        GL11.glClearColor(1f, 0f, 1f, 0f);
        startTime = System.currentTimeMillis();
    }

    @Override
    protected void onUpdate() {
        if (input.isKeyPressed(Key.SPACE)) {
            System.out.println("Pressed");
        }
        if (input.isKeyPressed(Key.SPACE)) {
            System.out.println("Pressed");
        }
        if (input.isKeyDown(Key.SPACE)) {
            //System.out.println("down");
            //exit();
        }
        if (input.isKeyReleased(Key.SPACE)) {
            System.out.println("Released");
        }
        //System.out.printf("%f, %f\n", input.getMouseX(), input.getMouseY());
    }

    @Override
    public void onFixedUpdate() {
        fixedUpdates++;
        if (System.currentTimeMillis() - startTime > 1000) {
            //System.out.println("Fixed Updates: " + fixedUpdates);
            System.out.println(time.deltaTime());
            fixedUpdates = 0;
            startTime = System.currentTimeMillis();
        }
    }

    @Override
    protected void onRender() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }

    @Override
    protected void onExit() {
        System.out.println("Exit");
    }

    public static void main(String[] args) {
        Config config = new Config();
        config.setWindowTitle("Test Game");
        config.enableVSync(true);
        config.setCursor(Cursor.NORMAL);
        config.setWindowSize(640, 480);
        config.setFullscreen(false);
        config.setCursor(Cursor.DISABLED);
        Game game = new TestGame(config);
        game.launch();
    }
}
