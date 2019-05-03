package ragna.ballot.rest;

import ragna.ballot.repository.model.Agenda;
import ragna.ballot.repository.model.User;
import ragna.ballot.repository.model.VoteReceipt;
import ragna.ballot.rest.dto.VoteDto;

import java.time.ZonedDateTime;

public interface Constants {
    
    User USER1 = User.builder().id(1L).name("John Connor").username("jconnor").build();
    User USER2 = User.builder().id(2L).name("Sarah Connor").username("sconnor").build();
    User USER3 = User.builder().id(3L).name("Arnold Schwarzenegger").username("t800").build();

    String USER1_DTO = "{\"username\": \"jconnor\", \"vote\": true }";

    Agenda AGENDA = Agenda.builder().id(1L).description("Should Destroy Skynet")
            .shortDescription("Destroy")
            .startVotingDate(ZonedDateTime.now())
            .scheduledEndVotingDate(ZonedDateTime.now().plusDays(1))
            .build();

    VoteReceipt VOTE_RECEIPT1 = VoteReceipt.builder()
            .agenda(AGENDA)
            .user(USER1)
            .timestamp(ZonedDateTime.now())
            .build();

    VoteReceipt VOTE_RECEIPT2 = VoteReceipt.builder()
            .agenda(AGENDA)
            .user(USER2)
            .timestamp(ZonedDateTime.now())
            .build();

    VoteReceipt VOTE_RECEIPT3 = VoteReceipt.builder()
            .agenda(AGENDA)
            .user(USER3)
            .timestamp(ZonedDateTime.now())
            .build();


}
