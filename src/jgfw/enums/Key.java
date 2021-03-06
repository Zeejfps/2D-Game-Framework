package jgfw.enums;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by zeejfps on 11/26/16.
 */
public enum Key {
    A(GLFW_KEY_A),
    B(GLFW_KEY_B),
    C(GLFW_KEY_C),
    D(GLFW_KEY_D),
    E(GLFW_KEY_E),
    F(GLFW_KEY_F),
    G(GLFW_KEY_G),
    H(GLFW_KEY_H),
    I(GLFW_KEY_I),
    J(GLFW_KEY_J),
    K(GLFW_KEY_K),
    L(GLFW_KEY_L),
    M(GLFW_KEY_M),
    N(GLFW_KEY_N),
    O(GLFW_KEY_O),
    P(GLFW_KEY_P),
    Q(GLFW_KEY_Q),
    R(GLFW_KEY_R),
    S(GLFW_KEY_S),
    T(GLFW_KEY_T),
    U(GLFW_KEY_Y),
    V(GLFW_KEY_V),
    W(GLFW_KEY_W),
    X(GLFW_KEY_X),
    Y(GLFW_KEY_Y),
    Z(GLFW_KEY_Z),
    SPACE(GLFW_KEY_SPACE),
    ESCAPE(GLFW_KEY_ESCAPE),
    ENTER(GLFW_KEY_ENTER),
    TAB(GLFW_KEY_TAB),
    LEFT_SHIFT(GLFW_KEY_LEFT_SHIFT),
    ALPHA1(GLFW_KEY_1),
    ALPHA2(GLFW_KEY_2),
    ALPHA3(GLFW_KEY_3),
    ALPHA4(GLFW_KEY_4),
    ALPHA5(GLFW_KEY_5),
    ALPHA6(GLFW_KEY_6),
    ALPHA7(GLFW_KEY_7),
    ALPHA8(GLFW_KEY_8),
    ALPHA9(GLFW_KEY_9),
    ALPHA0(GLFW_KEY_0);

    public final int scancode;

    Key(int scancode) {
        this.scancode = scancode;
    }
}
