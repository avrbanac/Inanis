package hr.avrbanac.inanis.inputs;

import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class KeyboardHandler extends GLFWKeyCallback {

    private static final boolean[] KEYS = new boolean[65536];

    // private CTOR - singleton class
    private KeyboardHandler() { }

    private static class KeyboardHandlerWrapper {
        static final KeyboardHandler KH_INSTANCE = new KeyboardHandler();
    }

    public static KeyboardHandler getInstance() {
        return KeyboardHandlerWrapper.KH_INSTANCE;
    }

    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        KEYS[key]= action != GLFW_RELEASE;
    }

    public static boolean isKeyDown(int keyCode) {
        return KEYS[keyCode];
    }
}
