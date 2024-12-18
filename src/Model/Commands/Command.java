package Model.Commands;

public abstract class  Command {
    private String key;
    private String value;
    public Command(String key, String value) {
        this.key = key;
        this.value = value;
    }
    public String getKey() {
        return key;
    }
    public String getValue() {
        return value;
    }
    public abstract void execute();
}
