package ragna.ballot.common.exception;

public class EntityNotFoundException extends BallotBaseException {
    public EntityNotFoundException(String entity, String field, String data) {
        super(String.format("Could not find %s by %s: %s", entity, field, data));
    }

    public EntityNotFoundException(String entity, Long id) {
        super(String.format("Could not find %s: %d", entity, id));
    }

}
