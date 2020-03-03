package hr.avrbanac.inanis.toolbox;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import static hr.avrbanac.inanis.InanisConfig.*;

public class Maths {

    // private CTOR! - no instances
    private Maths() { }

    public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry ,float rz, float scale) {
        return new Matrix4f().translate(translation).rotateAffineXYZ(rx, ry, rz).scale(scale);
    }

    public static Matrix4f createProjectionMatrix() {
        return new Matrix4f()
                .setPerspective(FOV, (float) DISPLAY_WIDTH / (float) DISPLAY_HEIGHT, NEAR_PLANE, FAR_PLANE);
    }
}
