package hr.avrbanac.inanis.internals;

import hr.avrbanac.inanis.InanisConfig;
import hr.avrbanac.inanis.loaders.Loader;
import hr.avrbanac.inanis.models.RawModel;
import hr.avrbanac.inanis.models.TestQuadModel;
import hr.avrbanac.inanis.models.TexturedModel;
import hr.avrbanac.inanis.renderers.Renderer;
import hr.avrbanac.inanis.shaders.ShaderProgram;
import hr.avrbanac.inanis.textures.ModelTexture;
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

        Loader loader = new Loader();
        Renderer renderer = new Renderer();
        renderer.init();
        RawModel model = loader.loadToVAO(TestQuadModel.VERTICES, TestQuadModel.TEXTURE_COORDS, TestQuadModel.INDICES);
        ModelTexture texture = new ModelTexture(loader.loadTexture("coffee"));
        TexturedModel texturedModel = new TexturedModel(model, texture);
        ShaderProgram shaderProgram = new ShaderProgram();

        while(running) {
            renderer.prepare();
            shaderProgram.start();
            renderer.render(texturedModel);
            shaderProgram.stop();
            displayManager.renderDisplay();
            displayManager.updateDisplay();

            // escape key pressed or close window button
            if(GLFW.glfwWindowShouldClose(displayManager.getWindowId())) {
                this.stop();
            }
        }

        shaderProgram.cleanUp();
        loader.cleanUp();
        displayManager.closeDisplay();
    }

    public boolean isRunning() {
        return running;
    }

    public GameState getGameState() {
        return gameState;
    }

}
