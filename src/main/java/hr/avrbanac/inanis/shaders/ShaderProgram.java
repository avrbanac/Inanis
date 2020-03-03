package hr.avrbanac.inanis.shaders;

import org.joml.Matrix4f;

import static hr.avrbanac.inanis.InanisConfig.FRAG_FILE_FULL;
import static hr.avrbanac.inanis.InanisConfig.VERT_FILE_FULL;

public class ShaderProgram extends AbstractShaderProgram {

    private int locationTransformationMatrix;
    private int locationProjectionMatrix;

    public ShaderProgram() {
        super(VERT_FILE_FULL, FRAG_FILE_FULL);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
    }

    @Override
    protected void getAllUniformLocations() {
        locationTransformationMatrix = super.getUniformLocation("transformationMatrix");
        locationProjectionMatrix = super.getUniformLocation("projectionMatrix");
    }

    public void loadTransformationMatrix(Matrix4f matrix) {
        super.loadMatrix(locationTransformationMatrix, matrix);
    }

    public void loadProjectionMatrix(Matrix4f matrix) {
        super.loadMatrix(locationProjectionMatrix, matrix);
    }
}
