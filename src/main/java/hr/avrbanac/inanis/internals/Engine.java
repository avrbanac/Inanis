package hr.avrbanac.inanis.internals;

import hr.avrbanac.inanis.InanisConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

public class Engine implements Runnable {
    private static final Logger LOG = LogManager.getLogger(Engine.class);

    private boolean running = false;
    private GameState gameState = GameState.INIT;
    private static DisplayManager displayManager = new DisplayManager();

    private void initDisplay() {
        displayManager.createDisplay();
    }

    public void start() {
        running = true;
        new Thread(this, InanisConfig.THREAD_NAME).start();
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        initDisplay();
        while(running) {

            update();
            render();

            // escape key pressed or close window button
            if(GLFW.glfwWindowShouldClose(displayManager.getWindowId())) {
                this.stop();
            }
        }
    }

    public boolean isRunning() {
        return running;
    }

    public GameState getGameState() {
        return gameState;
    }

    private void update() {
        displayManager.updateDisplay();
    }

    private void render() {
        displayManager.renderDisplay();
    }
}
