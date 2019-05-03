package ragna.ballot.rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    String shortMessage;
    String originalMessage;
}
