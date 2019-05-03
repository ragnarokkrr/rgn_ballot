package ragna.ballot.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class AgendaDto {
    private String shortDescription;
    private String description;
    @ApiModelProperty(example = "2019-05-01T08:30:00-03:00[Brazil/East]")
    private ZonedDateTime startVotingDate;
    @ApiModelProperty(example = "2019-05-03T08:30:00-03:00[Brazil/East]")
    private ZonedDateTime scheduledEndVotingDate;
}
