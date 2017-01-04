package jgfw;

/**
 * Created by zeejfps on 1/3/17.
 */
public abstract class Scene {

    public final App app;

    public Scene(App app) {
        this.app = app;
    }

    void load() {
        onLoad();
    }

    void update() {
        onUpdate();
    }

    void fixedUpdate() {
        onFixedUpdate();
    }

    void render() {
        onRender();
    }

    void unload() {
        onUnload();
    }

    protected abstract void onLoad();

    protected abstract void onUpdate();

    protected abstract void onFixedUpdate();

    protected abstract void onRender();

    protected abstract void onUnload();

}

