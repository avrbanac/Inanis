package hr.avrbanac.inanis.inputs;

import org.lwjgl.glfw.GLFWScrollCallback;

public class MouseScrollHandler extends GLFWScrollCallback {

    private static int mouseScrollX, mouseScrollY;

    // private CTOR - singleton class
    private MouseScrollHandler() { }

    private static class MouseScrollHandlerWrapper {
        static final MouseScrollHandler MCH_INSTANCE = new MouseScrollHandler();
    }

    public static MouseScrollHandler getInstance() {
        return MouseScrollHandlerWrapper.MCH_INSTANCE;
    }

    @Override
    public void invoke(long window, double xoffset, double yoffset) {
        mouseScrollX += (int)xoffset;
        mouseScrollY += (int)yoffset;
    }

    public static int getScrollX() {
        return mouseScrollX | (mouseScrollX = 0);
    }

    public static int getScrollY() {
        return mouseScrollY | (mouseScrollY = 0);
    }
}
