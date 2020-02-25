package hr.avrbanac.inanis;

public enum InanisErrorCode {

    INIT_GLFW (
            -1,
            "Failed to initialize GLFW window"),
    CREATE_GLFW (
            -2,
            "Failed to create GLFW window"),
    READ_FILE (
            -3,
            "Could not read file"),
    COMPILE_SHADER (
            -4,
            "Could not compile shader"),



    ;

    private final int code;
    private final String message;

    InanisErrorCode(int errorCode, String message) {
        this.code = errorCode;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
