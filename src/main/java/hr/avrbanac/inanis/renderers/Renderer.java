package hr.avrbanac.inanis.renderers;

import hr.avrbanac.inanis.models.RawModel;
import hr.avrbanac.inanis.models.TexturedModel;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Renderer {

    public void init() {
        GL11.glClearColor(0f,0f,0f,1f);
    }

    public void prepare() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }

    public void render(TexturedModel texturedModel) {
        RawModel model = texturedModel.getRawModel();
        // always when using VAO first bind it
        GL30.glBindVertexArray(model.getVaoId());
        // enable attribute list (exact attribute list)
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        // activate texture - first texture bank
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        // bind texture
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturedModel.getModelTexture().getTextureId());
        // now render it
//        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, model.getVertexCount()); ---> before using index buffer (v0.0.5)
        GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        // again, disable current when not using it anymore
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        // and ofc, unbind it
        GL30.glBindVertexArray(0);
    }
}
