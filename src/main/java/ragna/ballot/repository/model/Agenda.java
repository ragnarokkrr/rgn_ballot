package ragna.ballot.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.javatuples.Pair;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = {"ballotList", "voteReceiptList"})
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Agenda implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    @JsonIgnore
    private Integer version;

    @NotBlank(message = "short description is mandatory")
    private String shortDescription;
    private String description;
    @NotNull(message = "start voting date is mandatory")
    private ZonedDateTime startVotingDate;
    @NotNull(message = "end voting date is mandatory")
    private ZonedDateTime scheduledEndVotingDate;

    public Pair<Ballot,VoteReceipt> addVote(User user, Boolean yes) {
        Ballot ballot = Ballot.builder()
                .agenda(this)
                .yes(yes)
                .build();
        this.ballotList.add(ballot);


        VoteReceipt voteReceipt = VoteReceipt.builder()
                .agenda(this)
                .user(user)
                .timestamp(ZonedDateTime.now())
                .build();
        this.voteReceiptList.add(voteReceipt);

        return Pair.with(ballot, voteReceipt);
    }

    @OneToMany(
            mappedBy = "agenda",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    @JsonIgnore
    private final List<Ballot> ballotList = new ArrayList<>();

    public List<Ballot> getBallotList() {
        return Collections.unmodifiableList(ballotList);
    }

    @OneToMany(
            mappedBy = "agenda",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    @JsonIgnore
    private final List<VoteReceipt> voteReceiptList = new ArrayList<>();

    public List<VoteReceipt> getVoteReceiptList() {
        return Collections.unmodifiableList(voteReceiptList);
    }
}
