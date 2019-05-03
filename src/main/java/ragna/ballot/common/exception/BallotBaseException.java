package ragna.ballot.common.exception;

public class BallotBaseException extends RuntimeException{
    public BallotBaseException(String message) {
        super(message);
    }

    public BallotBaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
