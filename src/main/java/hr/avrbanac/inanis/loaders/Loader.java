package hr.avrbanac.inanis.loaders;

import hr.avrbanac.inanis.models.RawModel;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static hr.avrbanac.inanis.InanisConfig.MIPMAPPING_FACTOR;
import static hr.avrbanac.inanis.InanisConfig.getTextureFile;

public class Loader {

    private final List<Integer> vaos = new ArrayList<>();
    private final List<Integer> vbos = new ArrayList<>();
    private final List<Integer> texs = new ArrayList<>();

    public RawModel loadToVAO(float[] positions, float[] textureCoords, int[] indices) {
        int vaoId = createVAO();
        bindIndicesBuffer(indices);
        storeDataInAttributeList(0, 3, positions);
        storeDataInAttributeList(1, 2, textureCoords);
        unbindVAO();

        // vertex count is positions length / 3 (each vertex has 3 coords)
        return new RawModel(vaoId, indices.length);
    }

    public int loadTexture(String filename) {
        int[] pixels = null;
        int width = 0;
        int height = 0;
        try {
            BufferedImage image = ImageIO.read(new FileInputStream(getTextureFile(filename)));
            width = image.getWidth();
            height = image.getHeight();
            pixels = new int[width * height];
            image.getRGB(0, 0, width, height, pixels, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // color reordering
        int[] data = new int[width * height];
        for (int i = 0; i < width * height; i++) {
            int a = (pixels[i] & 0xff000000) >> 24;
            int r = (pixels[i] & 0xff0000) >> 16;
            int g = (pixels[i] & 0xff00) >> 8;
            int b = (pixels[i] & 0xff);
            // OpenGL wants it in this order
            data[i] = a << 24 | b << 16 | g << 8 | r;
        }

        int textureID = GL11.glGenTextures();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);

//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11. GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        GL11.glTexImage2D(
                GL11.GL_TEXTURE_2D,
                0, GL11.GL_RGBA,
                width, height, 0,
                GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, BufferUtils.createIntBuffer(data.length).put(data).flip());

        //mipmapping
        GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
        GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL14.GL_TEXTURE_LOD_BIAS, MIPMAPPING_FACTOR);

        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);

        texs.add(textureID);
        return textureID;
    }

    private int createVAO() {
        // generate new vertex array object and bind it to ID
        int vaoId = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoId);
        vaos.add(vaoId);

        return vaoId;
    }

    private void storeDataInAttributeList(int attributeListNumber, int coordinateSize, float[] data) {
        // crate empty VBO for storage and bind it to ID (type of the buffer is GL_ARRAY_BUFFER)
        int vboId = GL15.glGenBuffers();
        vbos.add(vboId);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);

        FloatBuffer buffer = storeDataInFloatBuffer(data);
        // usage value: static; we will not be editing this value
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);

        // put VBO into VAO (vertex size = 3; stride is distance between vertices, pointer is offset where to start)
        GL20.glVertexAttribPointer(attributeListNumber, coordinateSize, GL11.GL_FLOAT, false, 0,0);

        //unbind current when not working with it anymore
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    public void bindIndicesBuffer(int[] indices) {
        // generate buffer, bind (ELEMENT_ARRAY), work with it and then unbind
        int vboId = GL15.glGenBuffers();
        vbos.add(vboId);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboId);

        IntBuffer buffer = storeDataInIntBuffer(indices);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
    }

    private IntBuffer storeDataInIntBuffer(int[] data) {
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        // flip buffer so it is no longer prepared to be written but to be read
        return buffer.flip();
    }

    private FloatBuffer storeDataInFloatBuffer(float[] data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        // flip buffer so it is no longer prepared to be written but to be read
        return buffer.flip();
    }

    private void unbindVAO() {
        // unbind currently bound VAO
        GL30.glBindVertexArray(0);
    }

    public void cleanUp() {
        vaos.forEach(GL30::glDeleteVertexArrays);
        vbos.forEach(GL15::glDeleteBuffers);
        texs.forEach(GL11::glDeleteTextures);
    }

    public static IntBuffer createIntBuffer(int[] array) {
        IntBuffer result = ByteBuffer.allocateDirect(array.length << 2).order(ByteOrder.nativeOrder()).asIntBuffer();
        result.put(array).flip();
        return result;
    }
}
