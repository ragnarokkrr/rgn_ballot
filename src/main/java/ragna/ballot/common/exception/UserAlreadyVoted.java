package ragna.ballot.common.exception;

import ragna.ballot.repository.model.Agenda;
import ragna.ballot.repository.model.User;

public class UserAlreadyVoted extends VotingException {
    public UserAlreadyVoted(Agenda agenda, User user) {
        super(String.format("User %s already voted for %s", user, agenda));
    }
}
