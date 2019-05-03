package ragna.ballot.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ragna.ballot.business.BallotService;
import ragna.ballot.repository.model.BallotCountVo;
import ragna.ballot.repository.model.VoteReceipt;
import ragna.ballot.rest.dto.VoteDto;

import java.util.List;

@RestController("/v1")
@Api("/v1/ballot")
@Slf4j
public class BallotController {
    @Autowired
    private BallotService ballotService;

    @ApiOperation(value = "Vote for Agenda.")
    @PostMapping("/ballot/{agendaId}/vote")
    public VoteReceipt vote(@PathVariable Long agendaId,
                            @RequestBody VoteDto vote) {
        log.info("Voting {} {}", agendaId, vote);
        return ballotService.vote(agendaId, vote.getUsername(), vote.getVote());
    }

    @ApiOperation(value = "Return Ballot Results.")
    @GetMapping("/ballot/{agendaId}/results")
    public List<BallotCountVo> votingResults(@PathVariable Long agendaId) {
        return ballotService.votingResults(agendaId);
    }
}
