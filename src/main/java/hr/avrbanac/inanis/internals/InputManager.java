package hr.avrbanac.inanis.internals;

import hr.avrbanac.inanis.inputs.KeyboardHandler;
import hr.avrbanac.inanis.inputs.MouseButtonHandler;
import hr.avrbanac.inanis.inputs.MousePosHandler;
import hr.avrbanac.inanis.inputs.MouseScrollHandler;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;

import static org.lwjgl.glfw.GLFW.*;

public class InputManager {

    // input callbacks
    private static GLFWKeyCallback keyCallback;
    private static GLFWCursorPosCallback mousePosCallback;
    private static GLFWMouseButtonCallback mouseButtonCallback;
    private static GLFWScrollCallback mouseScrollCallback;

    // protected CTOR - singleton
    private InputManager() { }

    private static class InputManagerWrapper {
        static final InputManager INPUT_MANAGER_INSTANCE = new InputManager();
    }

    public static InputManager getInstance() {
        return InputManagerWrapper.INPUT_MANAGER_INSTANCE;
    }

    public void createInputCallbacks(long windowId) {
        glfwSetKeyCallback(windowId, keyCallback = KeyboardHandler.getInstance());
        glfwSetCursorPosCallback(windowId, mousePosCallback = MousePosHandler.getInstance());
        glfwSetMouseButtonCallback(windowId, mouseButtonCallback = MouseButtonHandler.getInstance());
        glfwSetScrollCallback(windowId, mouseScrollCallback = MouseScrollHandler.getInstance());
    }

    public void destroyInputCallbacks() {
        keyCallback.free();
        mousePosCallback.free();
        mouseButtonCallback.free();
        mouseScrollCallback.free();
    }
}
