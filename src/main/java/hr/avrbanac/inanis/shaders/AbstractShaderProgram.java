package hr.avrbanac.inanis.shaders;

import hr.avrbanac.inanis.InanisErrorCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static hr.avrbanac.inanis.Inanis.exitWithError;
import static hr.avrbanac.inanis.InanisConfig.MAX_SHADER_LOG_SIZE;

public abstract class AbstractShaderProgram {
    private static final Logger LOG = LogManager.getLogger(AbstractShaderProgram.class);
    private int programId;
    private int vertShaderId;
    private int fragShaderId;

    protected abstract void bindAttributes();
    public AbstractShaderProgram(String vertFile, String fragFile) {
        vertShaderId = loadShader(vertFile, GL20.GL_VERTEX_SHADER);
        fragShaderId = loadShader(fragFile, GL20.GL_FRAGMENT_SHADER);
        programId = GL20.glCreateProgram();
        GL20.glAttachShader(programId, vertShaderId);
        GL20.glAttachShader(programId, fragShaderId);
        bindAttributes();
        GL20.glLinkProgram(programId);
        GL20.glValidateProgram(programId);
    }

    public void start() {
        GL20.glUseProgram(programId);
    }

    public void stop() {
        GL20.glUseProgram(0);
    }

    public void cleanUp() {
        stop();
        GL20.glDetachShader(programId, vertShaderId);
        GL20.glDetachShader(programId, fragShaderId);
        GL20.glDeleteShader(vertShaderId);
        GL20.glDeleteShader(fragShaderId);
        GL20.glDeleteProgram(programId);
    }

    // bind attribute from within object (needs to use programId)
    protected void bindAttribute(int attribute, String variableName) {
        // binding variable list from VAO to variable in shader program
        GL20.glBindAttribLocation(programId, attribute, variableName);
    }

    private static int loadShader(String file, int type) {
        StringBuilder shaderSrc = new StringBuilder();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = bufferedReader.readLine()) != null) {
                shaderSrc.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            exitWithError(InanisErrorCode.READ_FILE, file);
        }

        int shaderId = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderId, shaderSrc);
        GL20.glCompileShader(shaderId);
        if (GL20.glGetShaderi(shaderId, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            exitWithError(InanisErrorCode.COMPILE_SHADER, GL20.glGetShaderInfoLog(shaderId, MAX_SHADER_LOG_SIZE));
        }

        return shaderId;
    }
}
