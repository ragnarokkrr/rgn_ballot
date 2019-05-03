package ragna.ballot.rest.config;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ragna.ballot.common.exception.EntityNotFoundException;
import ragna.ballot.common.exception.VotingException;
import ragna.ballot.rest.dto.ErrorResponse;

@ControllerAdvice(basePackages = "ragna.ballot.rest")
public class RestControllerAdvice {
    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse employeeNotFoundHandler(EntityNotFoundException ex) {
        return ErrorResponse.builder().shortMessage("User error").originalMessage(ex.getMessage()).build();
    }

    @ResponseBody
    @ExceptionHandler({DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse dataAccessHandler(Exception ex) {
        return ErrorResponse.builder().shortMessage("Data Acess Error").originalMessage(ex.getMessage()).build();
    }

    @ResponseBody
    @ExceptionHandler({VotingException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse votingHandler(Exception ex) {
        return ErrorResponse.builder().shortMessage("Voting Error").originalMessage(ex.getMessage()).build();
    }

    @ResponseBody
    @ExceptionHandler({Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ErrorResponse defaultHandler(Exception ex) {
        return ErrorResponse.builder().shortMessage("Unhandled Error").originalMessage(ex.getMessage()).build();
    }

}
