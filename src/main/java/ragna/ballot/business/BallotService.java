package ragna.ballot.business;

import lombok.extern.slf4j.Slf4j;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ragna.ballot.common.exception.FinishedVotingException;
import ragna.ballot.common.exception.UserAlreadyVoted;
import ragna.ballot.common.exception.VotingException;
import ragna.ballot.repository.AgendaRepository;
import ragna.ballot.repository.BallotRepository;
import ragna.ballot.repository.UserRepository;
import ragna.ballot.repository.VoteReceiptRepository;
import ragna.ballot.repository.model.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BallotService {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VoteReceiptRepository voteReceiptRepository;

    @Autowired
    private BallotRepository ballotRepository;

    public VoteReceipt vote(Long agendaId, String username, Boolean yes) {
        Agenda agenda = agendaRepository.findById(agendaId)
                .orElseThrow(() -> new VotingException("Agenda not found: " + agendaId));

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new VotingException("User not found: " + agendaId));

        if (ZonedDateTime.now().isAfter(agenda.getScheduledEndVotingDate())) {
            log.error("Voting already finished: {} {}", agenda, user);
            throw new FinishedVotingException(agenda);
        }

        Optional<VoteReceipt> userAlreadyVoted = voteReceiptRepository.findByUserIdAndAgendaId(user.getId(),
                agenda.getId());

        if (userAlreadyVoted.isPresent()) {
            log.error("User already voted in this agenda: {} {}", agenda, user);
            throw new UserAlreadyVoted(agenda, user);
        }

        Pair<Ballot, VoteReceipt> objects = agenda.addVote(user, yes);

        log.debug("Vote added {} {}", objects.getValue0(), objects.getValue1());

        agendaRepository.saveAndFlush(agenda);

        VoteReceipt voteReceipt = voteReceiptRepository.findByUserIdAndAgendaId(user.getId(), agenda.getId())
                .orElseThrow(() -> new VotingException("User not found: " + agendaId));

        return voteReceipt;
    }

    public List<BallotCountVo> votingResults(Long agendaId) {
        return ballotRepository.countBallotResults(agendaId);
    }
}
