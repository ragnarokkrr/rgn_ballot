package ragna.ballot.repository.model;

import lombok.Data;

@Data
public class BallotCountVo {
    public BallotCountVo(Boolean result, Long count) {
        this.result = result;
        this.count = count;
    }

    private Boolean result;
    private Long count;
}
