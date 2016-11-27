import jgfw.Config;
import jgfw.Game;
import jgfw.Scene;
import jgfw.enums.Cursor;
import jgfw.enums.Key;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

/**
 * Created by zeejfps on 11/16/16.
 */
public class TestScene extends Scene {

    @Override
    public void onLoad() {

    }

    @Override
    public void onStart(Game game) {
        System.out.println("Begin");
        GL11.glClearColor(1f, 0f, 1f, 0f);
        try {
            BitmapFont font = BitmapFont.load("8bit.fnt");
            System.out.println(font.info.padding);
            System.out.println(font.info.spacing);
            System.out.println(font.common.pages);
            for (BitmapFont.Page p : font.pages) {
                System.out.println(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdate(Game game) {
        if (game.input.getKey(Key.SPACE)) {
            System.out.println("Pressed");
        }
        //System.out.printf("%f, %f\n", game.input.getMouseX(), game.input.getMouseY());
    }

    @Override
    public void onRender(Game game) {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void onUnload() {

    }

    public static void main(String[] args) {
        Config config = new Config();
        config.setWindowTitle("Test Game");
        config.enableVSync(true);
        config.setCursorMode(Cursor.NORMAL);
        Game game = new Game(config);
        game.launch(new TestScene());
    }
}
