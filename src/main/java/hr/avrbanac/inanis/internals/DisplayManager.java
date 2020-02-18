package hr.avrbanac.inanis.internals;

import hr.avrbanac.inanis.inputs.KeyboardHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import java.nio.IntBuffer;

import static hr.avrbanac.inanis.InanisConfig.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class DisplayManager {
    private static final Logger LOG = LogManager.getLogger(DisplayManager.class);

    // input manager with input callbacks
    private InputManager inputManager = InputManager.getInstance();

    public static final double GLFW_TIME_TO_MILLISECONDS = 1000d;
    private long windowId = 0L;
    private int width = 0;
    private int height = 0;

    // time at the end of the last frame (in milliseconds)
    private long lastFrameTime;
    // time taken to render the previous frame (in seconds)
    private float delta;

    public long getWindowId() {
        return windowId;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void createDisplay() {
        // initialize display
        if (!glfwInit()) {
            LOG.error("Failed to initialize GLFW window");
            System.exit(-1);
        }

        // create window
        windowId = glfwCreateWindow(DISPLAY_WIDTH, DISPLAY_HEIGHT, DISPLAY_TITLE, NULL, NULL);
        if (windowId == NULL) {
            glfwTerminate();
            LOG.error("Failed to create GLFW window");
            System.exit(-2);
        }

        // links OpenGL context to the current thread
        glfwMakeContextCurrent(windowId);

        // cap the framerate to 60 (vsync)
        glfwSwapInterval(GLFW_TRUE);

        // create capabilities instance - current for the current thread (& OpenGL context)
        GL.createCapabilities();

        glfwShowWindow(windowId);

        IntBuffer w = BufferUtils.createIntBuffer(1);
        IntBuffer h = BufferUtils.createIntBuffer(1);
        glfwGetWindowSize(windowId, w, h);
        width = w.get(0);
        height = h.get(0);

        inputManager.createInputCallbacks(windowId);

        lastFrameTime = getCurrentTime();
        LOG.info("Created display window with id: {}", windowId);
    }

    public void renderDisplay() {
        // checks for OpenGL errors (TODO: is this optimal way to do it?)
        int error = GL11.glGetError();
        if (error != GL11.GL_NO_ERROR) LOG.error("OpenGL error with following code occurred: {}", error);

        // moved this to renderer
//        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        // Swaps out buffers - updates display
        glfwSwapBuffers(windowId);
    }

    public void updateDisplay() {
        // Polls for any window events such as the window closing etc.
        glfwPollEvents();

        if(KeyboardHandler.isKeyDown(GLFW_KEY_SPACE)) {
            LOG.info("SPACE!!!");
        }
        long currentFrameTime = getCurrentTime();
        delta = (currentFrameTime - lastFrameTime) / 1000f;
        lastFrameTime = currentFrameTime;
    }

    public float getFrameTimeSeconds() {
        return delta;
    }

    public void closeDisplay() {
        glfwDestroyWindow(windowId);
        inputManager.destroyInputCallbacks();
        glfwTerminate();
    }

    private static long getCurrentTime() {
        return (long)(glfwGetTime() * GLFW_TIME_TO_MILLISECONDS);
    }
}
