package hr.avrbanac.inanis.loaders;

import hr.avrbanac.inanis.models.RawModel;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

public class Loader {

    private List<Integer> vaos = new ArrayList<>();
    private List<Integer> vbos = new ArrayList<>();

    public RawModel loadToVAO(float[] positions) {
        int vaoId = createVAO();
        storeDataInAttributeList(0, positions);
        unbindVAO();

        // vertex count is positions length / 3 (each vertex has 3 coords)
        return new RawModel(vaoId, positions.length/3);
    }

    private int createVAO() {
        // generate new vertex array object and bind it to ID
        int vaoId = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoId);
        vaos.add(vaoId);

        return vaoId;
    }

    private void storeDataInAttributeList(int attributeListNumber, float[] data) {
        // crate empty VBO for storage and bind it to ID (type of the buffer is GL_ARRAY_BUFFER)
        int vboId = GL15.glGenBuffers();
        vbos.add(vboId);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);

        FloatBuffer buffer = storeDataInFloatBuffer(data);
        // usage value: static; we will not be editing this value
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);

        // put VBO into VAO (vertex size = 3; stride is distance between vertices, pointer is offset where to start)
        GL20.glVertexAttribPointer(attributeListNumber, 3, GL11.GL_FLOAT, false, 0,0);

        //unbind current when not working with it anymore
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    private void unbindVAO() {
        // unbind currently bound VAO
        GL30.glBindVertexArray(0);
    }

    public void cleanUp() {
        vaos.forEach(integer -> GL30.glDeleteVertexArrays(integer));
        vbos.forEach(integer -> GL15.glDeleteBuffers(integer));
    }

    private FloatBuffer storeDataInFloatBuffer(float[] data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        // flip buffer so it is no longer prepared to be written but to be read
        buffer.flip();

        return buffer;
    }
}
