package hr.avrbanac.inanis.models;

public class TestQuadModel {

    // this was before using index buffer (v0.0.5)
//    public static final float[] QUAD_VERTICES = {
//            // lower left triangle
//            -0.5f,  0.5f,   0f,
//            -0.5f, -0.5f,   0f,
//             0.5f, -0.5f,   0f,
//            // upper right triangle
//             0.5f, -0.5f,   0f,
//             0.5f,  0.5f,   0f,
//            -0.5f,  0.5f,   0f
//    };

    public static final float[] VERTICES = {
            -0.5f,   0.5f,   0f,    //V0
            -0.5f,  -0.5f,   0f,    //V1
             0.5f,  -0.5f,   0f,    //V2
             0.5f,   0.5f,   0f,    //V3
    };

    public static final int[] INDICES = {
            0,1,3,      // top left triangle        (V0, V1, V3)
            3,1,2       // bottom right triangle    (V3, V1, V2)
    };

    public static final float[] TEXTURE_COORDS = {
            0,0,    // V0
            0,1,    // V1
            1,1,    // V2
            1,0     // V3
    };

    // private CTOR - no need for instance
    private TestQuadModel() { }
}
