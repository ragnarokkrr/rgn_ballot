package ragna.ballot.rest.mapper;

import org.springframework.stereotype.Component;
import ragna.ballot.repository.model.Agenda;
import ragna.ballot.rest.dto.AgendaDto;

@Component
public class AgendaMapper {
    public Agenda fromDto(AgendaDto newAgenda) {
        return Agenda.builder()
                .description(newAgenda.getDescription())
                .shortDescription(newAgenda.getShortDescription())
                .startVotingDate(newAgenda.getStartVotingDate())
                .scheduledEndVotingDate(newAgenda.getScheduledEndVotingDate())
                .build();
    }

}
