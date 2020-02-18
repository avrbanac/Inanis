package hr.avrbanac.inanis.models;

public class TestQuadModel {

    public static final float[] QUAD_VERTICES = {
            // lower left triangle
            -0.5f,  0.5f,   0f,
            -0.5f, -0.5f,   0f,
             0.5f, -0.5f,   0f,
            // upper right triangle
             0.5f, -0.5f,   0f,
             0.5f,  0.5f,   0f,
            -0.5f,  0.5f,   0f
    };

    // private CTOR - no need for instance
    private TestQuadModel() { }
}
