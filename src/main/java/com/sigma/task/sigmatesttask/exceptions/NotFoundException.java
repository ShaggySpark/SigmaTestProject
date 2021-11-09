package com.sigma.task.sigmatesttask.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No entry found")  // 404
public class NotFoundException extends RuntimeException {
}
