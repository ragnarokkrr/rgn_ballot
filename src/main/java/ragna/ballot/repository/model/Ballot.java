package ragna.ballot.repository.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Stores one vote for a given agenda.
 * Does not store UserDto identity to preserve his confidentiality.
 * {link @VoteReceipt} records user vote for this agenda.
 */
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ballot implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Integer version;

    private Boolean yes;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "agenda_id")
    private Agenda agenda;
}
