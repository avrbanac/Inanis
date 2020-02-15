package hr.avrbanac.inanis.inputs;

import org.lwjgl.glfw.GLFWMouseButtonCallback;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LAST;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseButtonHandler extends GLFWMouseButtonCallback {

    private static final boolean[] BUTTONS = new boolean[GLFW_MOUSE_BUTTON_LAST + 1];
    private static final boolean[] BUTTONS_CHANGE = new boolean[GLFW_MOUSE_BUTTON_LAST + 1];

    // private CTOR - singleton class
    private MouseButtonHandler() { }

    private static class MouseButtonHandlerWrapper {
        static final MouseButtonHandler MBH_INSTANCE = new MouseButtonHandler();
    }

    public static MouseButtonHandler getInstance() {
        return MouseButtonHandlerWrapper.MBH_INSTANCE;
    }

    @Override
    public void invoke(long window, int button, int action, int mods) {
        boolean newState = action != GLFW_RELEASE;

        BUTTONS_CHANGE[button] = (BUTTONS[button] != newState);
        BUTTONS[button]= newState;

    }

    public static boolean isButtonDown(int buttonCode) {
        return BUTTONS[buttonCode];
    }

    public static boolean hasButtonChangedState(int buttonCode) {
        return BUTTONS_CHANGE[buttonCode] | (BUTTONS_CHANGE[buttonCode] = false);
    }

}
