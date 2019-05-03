package ragna.ballot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ragna.ballot.repository.model.Ballot;
import ragna.ballot.repository.model.BallotCountVo;

import java.util.List;

@Repository
public interface BallotRepository extends JpaRepository<Ballot, Long> {


    @Query("SELECT "
            + " new ragna.ballot.repository.model.BallotCountVo(b.yes, COUNT(b)) "
            + "FROM "
            + " Ballot b "
            + "WHERE"
            + " b.agenda.id = :agendaId "
            + "GROUP BY "
            + " b.yes")
    List<BallotCountVo> countBallotResults(@Param("agendaId") Long agendaId);
}
