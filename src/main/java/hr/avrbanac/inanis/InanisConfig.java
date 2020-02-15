package hr.avrbanac.inanis;

public class InanisConfig {

    // GLOBAL block:
    public static final String THREAD_NAME          = "inanis_thread";
    public static final String DISPLAY_TITLE        = "Inanis";

    // DISPLAY block:
    public static final int DISPLAY_WIDTH           = 1280;
    public static final int DISPLAY_HEIGHT          = 720;

    // SHADER block:
    public static final String VERTEX_FILE          = "res/shaders/shaderVert.glsl";
    public static final String FRAGMENT_FILE        = "res/shaders/shaderFrag.glsl";
    public static final int MAX_SHADER_LOG_SIZE     = 500;

    // INPUT block:
    public static final boolean MOUSE_Y_INVERT      = false;

    // private CTOR - no need for instance
    private InanisConfig() { }
}
