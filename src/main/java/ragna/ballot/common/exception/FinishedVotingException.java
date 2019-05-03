package ragna.ballot.common.exception;

import ragna.ballot.repository.model.Agenda;

public class FinishedVotingException extends VotingException{
    public FinishedVotingException(Agenda agenda) {
        super(String.format("Voting {%d, %s, %s} is finished: "
                , agenda.getId()
                , agenda.getShortDescription()
                , agenda.getScheduledEndVotingDate()));
    }
}
