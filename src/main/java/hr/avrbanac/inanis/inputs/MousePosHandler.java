package hr.avrbanac.inanis.inputs;

import org.lwjgl.glfw.GLFWCursorPosCallback;

import static hr.avrbanac.inanis.InanisConfig.DISPLAY_HEIGHT;
import static hr.avrbanac.inanis.InanisConfig.MOUSE_Y_INVERT;

public class MousePosHandler extends GLFWCursorPosCallback {

    private static int mouseX, mouseY, mouseDX, mouseDY;

    // private CTOR - singleton class
    private MousePosHandler() {
        MousePosHandler.mouseX = 0;
        MousePosHandler.mouseY = 0;
        MousePosHandler.mouseDX = 0;
        MousePosHandler.mouseDY = 0;
    }

    private static class MousePosHandlerWrapper {
        static final MousePosHandler MPH_INSTANCE = new MousePosHandler();
    }

    public static MousePosHandler getInstance() {
        return MousePosHandlerWrapper.MPH_INSTANCE;
    }

    @Override
    public void invoke(long window, double xpos, double ypos) {
        int yCorrected = MOUSE_Y_INVERT ? (int)ypos : DISPLAY_HEIGHT - (int)ypos;

        mouseDX += (int)xpos - mouseX;
        mouseDY += yCorrected - mouseY;

        mouseX = (int)xpos;
        mouseY = yCorrected;
    }

    public static int getX() {
        return mouseX;
    }

    public static int getY() {
        return mouseY;
    }

    public static int getDX() {
        return mouseDX | (mouseDX = 0);
    }

    public static int getDY() {
        return mouseDY | (mouseDY = 0);
    }

    public static void resetDPos() {
        mouseDX = 0;
        mouseDY = 0;
    }
}
