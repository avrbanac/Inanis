package hr.avrbanac.inanis.renderers;

import hr.avrbanac.inanis.models.RawModel;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Renderer {

    public void init() {
        GL11.glClearColor(0f,0f,0f,0f);
    }

    public void prepare() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }

    public void render(RawModel model) {
        // always when using VAO first bind it
        GL30.glBindVertexArray(model.getVaoId());
        // enable attribute list (exact attribute list)
        GL20.glEnableVertexAttribArray(0);
        // now render it
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, model.getVertexCount());
        // again, disable current when not using it anymore
        GL20.glDisableVertexAttribArray(0);
        // and ofc, unbind it
        GL30.glBindVertexArray(0);
    }
}
