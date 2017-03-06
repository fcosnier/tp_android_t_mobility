package thales.fr.firsttp.event;


/**
 * AppEvent.class
 *
 * This class is an object Event describing the App event
 * It contains a event type and status with data if necessary.
 */
public class AppEvent {

    // Enum of the different type of the Network Event
    public enum Type {
        GET,
        POST,
        PUT,
        DELETE,
        UI,
        FRAGMENT,
        SYNC,
    }

    // Enum of the different Status of teh response received of the REST Event
    public enum Status {
        CHANGE,
    }

    private Type type;
    private Status status;
    private String message;
    private long code;
    private Object object;

    public AppEvent(){
    }

    public AppEvent(Type type) {
        this.type = type;
    }

    public AppEvent(Type type, Status status) {
        this.type = type;
        this.status = status;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
