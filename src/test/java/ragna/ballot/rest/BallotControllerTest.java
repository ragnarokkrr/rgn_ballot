package ragna.ballot.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ragna.ballot.business.BallotService;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BallotControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BallotService ballotService;

    @Test
    public void registeredUser_ShouldReceiveAVoteReceipt() throws Exception {

        when(ballotService.vote(Constants.AGENDA.getId(), Constants.USER1.getUsername(), true))
                .thenReturn(Constants.VOTE_RECEIPT1);

        when(ballotService.vote(Constants.AGENDA.getId(), Constants.USER2.getUsername(), true))
                .thenReturn(Constants.VOTE_RECEIPT2);

        when(ballotService.vote(Constants.AGENDA.getId(), Constants.USER3.getUsername(), false))
                .thenReturn(Constants.VOTE_RECEIPT3);

        mockMvc.perform(post("/ballot/1/vote")
                .contentType(APPLICATION_JSON_UTF8)
                .content(Constants.USER1_DTO))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.username").value(Constants.USER1.getUsername()))
                .andExpect(jsonPath("$.agenda.description").value(Constants.AGENDA.getDescription()));

    }

}
