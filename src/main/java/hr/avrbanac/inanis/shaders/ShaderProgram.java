package hr.avrbanac.inanis.shaders;

import static hr.avrbanac.inanis.InanisConfig.FRAG_FILE_FULL;
import static hr.avrbanac.inanis.InanisConfig.VERT_FILE_FULL;

public class ShaderProgram extends AbstractShaderProgram {

    public ShaderProgram() {
        super(VERT_FILE_FULL, FRAG_FILE_FULL);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
    }
}
