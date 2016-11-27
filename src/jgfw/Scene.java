package jgfw;

/**
 * Created by zeejfps on 11/16/16.
 */
public abstract class Scene {

    final void load() {
        onLoad();
    }

    final void start(Game game) {
        onStart(game);
    }

    final void update(Game game) {
        onUpdate(game);
    }

    final void render(Game game) {
        onRender(game);
    }

    final void unload() {
        onUnload();
    }

    public abstract void onLoad();

    public abstract void onStart(Game game);

    public abstract void onUpdate(Game game);

    public abstract void onRender(Game game);

    public abstract void onUnload();
}
