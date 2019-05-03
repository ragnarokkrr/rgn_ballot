package ragna.ballot.rest.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteDto {


    private String username;
    private Boolean vote;
}
