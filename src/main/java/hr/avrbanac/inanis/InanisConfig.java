package hr.avrbanac.inanis;

public class InanisConfig {

    // GLOBAL block:
    public static final String THREAD_NAME          = "inanis_thread";
    public static final String DISPLAY_TITLE        = "Inanis";
    public static final ClassLoader CLASS_LOADER    = InanisConfig.class.getClassLoader();

    // DISPLAY block:
    public static final int DISPLAY_WIDTH           = 1280;
    public static final int DISPLAY_HEIGHT          = 720;

    // SHADER block:
    public static final String VERTEX_FILE          = "shaders/shaderVert.glsl";
    public static final String VERT_FILE_FULL       = CLASS_LOADER.getResource(VERTEX_FILE).getFile();
    public static final String FRAGMENT_FILE        = "shaders/shaderFrag.glsl";
    public static final String FRAG_FILE_FULL       = CLASS_LOADER.getResource(FRAGMENT_FILE).getFile();
    public static final int MAX_SHADER_LOG_SIZE     = 500;

    // TEXTURE block:
    public static final String TEXTURE_TYPE         = "PNG";
    public static final String TEXTURE_LOCATION     = "textures/";

    // INPUT block:
    public static final boolean MOUSE_Y_INVERT      = false;

    // OpenGl block:
    public static final float MIPMAPPING_FACTOR     = -0.4f;
    public static final boolean FIX_SKYBOX_SEAMS    = false;
    public static final float FOV                   = 70f;
    public static final float NEAR_PLANE            = 0.1f;
    public static final float FAR_PLANE             = 1000f;

    // private CTOR - no need for instance
    private InanisConfig() { }

    public static String getTextureFile(String filename) {
        return CLASS_LOADER.getResource(
                TEXTURE_LOCATION + filename + '.' + TEXTURE_TYPE.toLowerCase())
                .getFile();
    }
}
