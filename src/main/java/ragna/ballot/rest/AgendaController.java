package ragna.ballot.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ragna.ballot.common.exception.EntityNotFoundException;
import ragna.ballot.repository.AgendaRepository;
import ragna.ballot.repository.model.Agenda;
import ragna.ballot.rest.dto.AgendaDto;
import ragna.ballot.rest.mapper.AgendaMapper;

import java.util.List;

@RestController("/v1")
@Api("/v1/agenda")
public class AgendaController {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private AgendaMapper agendaMapper;

    @ApiOperation(value = "List agendas.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/agendas", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Agenda> listAgendas() {
        return agendaRepository.findAll();
    }

    @ApiOperation(value = "Create agenda.")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/agendas", produces = MediaType.APPLICATION_JSON_VALUE)
    public Agenda createAgenda(@RequestBody AgendaDto newAgenda) {
        Agenda agenda = agendaMapper.fromDto(newAgenda);

        return agendaRepository.save(agenda);
    }

    @ApiOperation(value = "Find agenda by id.")
    @GetMapping("/agendas/{id}")
    Agenda findAgendaById(@PathVariable Long id) {
        return agendaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agenda", id));
    }
}
