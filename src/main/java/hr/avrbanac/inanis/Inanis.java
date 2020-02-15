package hr.avrbanac.inanis;

import hr.avrbanac.inanis.internals.Engine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFWErrorCallback;

import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;

public class Inanis {
    private static final Logger LOG = LogManager.getLogger(Inanis.class);
    private static GLFWErrorCallback errorCallback = GLFWErrorCallback.createPrint();

    public static void main(String[] args) {
        LOG.info("Starting Inanis...");
        glfwSetErrorCallback(errorCallback);

        Engine engine = new Engine();
        engine.start();

        // release error callback
        errorCallback.free();
    }
}
