package hello.Exception;

/**
 * Created by njucong on 2017/5/30.
 */
public class StorageException extends Exception {
    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
