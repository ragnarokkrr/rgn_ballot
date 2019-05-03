package ragna.ballot.common.exception;

public class VotingException extends BallotBaseException {
    public VotingException(String message) {
        super(message);
    }

    public VotingException(String message, Throwable cause) {
        super(message, cause);
    }
}
