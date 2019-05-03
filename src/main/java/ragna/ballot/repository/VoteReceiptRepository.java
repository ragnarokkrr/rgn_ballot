package ragna.ballot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ragna.ballot.repository.model.VoteReceipt;

import java.util.Optional;

@Repository
public interface VoteReceiptRepository extends JpaRepository<VoteReceipt, Long> {

    @Query("SELECT vr "
            + "FROM VoteReceipt vr "
            + " JOIN vr.agenda a "
            + " JOIN vr.user u "
            + "WHERE u.id = :userId "
            + " AND a.id = :agendaId")
    Optional<VoteReceipt> findByUserIdAndAgendaId(@Param("userId") Long userId,
                                                  @Param("agendaId") Long agendaId);
}
