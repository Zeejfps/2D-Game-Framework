package jgfw;

/**
 * Created by zeejfps on 12/4/16.
 */
public final class Time {

    public static final long NS_PER_SEC = 1000000000;

    private float deltaTime;
    private float elapsedTime;

    Time() {}

    void setDeltaTime(float deltaTime) {
        this.elapsedTime += deltaTime;
        this.deltaTime = deltaTime;
    }

    public float deltaTime() {
        return deltaTime;
    }

    public float elapsedTime() {
        return elapsedTime;
    }

}
