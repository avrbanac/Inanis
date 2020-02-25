package hr.avrbanac.inanis;

import hr.avrbanac.inanis.internals.Engine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFWErrorCallback;

import static hr.avrbanac.inanis.InanisConfig.VERT_FILE_FULL;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;

public class Inanis {
    private static final Logger LOG = LogManager.getLogger(Inanis.class);
    private static GLFWErrorCallback errorCallback = GLFWErrorCallback.createPrint();

    public static void main(String[] args) {
        LOG.info("Starting Inanis...{}",VERT_FILE_FULL);
        glfwSetErrorCallback(errorCallback);

        Engine engine = new Engine();
        engine.start();

        // release error callback
        errorCallback.free();
    }

    public static void exitWithError(InanisErrorCode errorCode) {
        exitWithError(errorCode, null);
    }

    public static void exitWithError(InanisErrorCode errorCode, String additionalMessage) {
        if (additionalMessage != null) {
            LOG.error("Fatal error occurred: {}, {} >>> exiting with error: {}",
                    errorCode.getMessage(), additionalMessage, errorCode.getCode());
        } else {
            LOG.error("Fatal error occurred: {} >>> exiting with error: {}",
                    errorCode.getMessage(), errorCode.getCode());
        }
        System.exit(errorCode.getCode());
    }
}
