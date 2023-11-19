package com.yuzarsif.youcontribute.exception;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class DuplicatedRepositoryException extends RuntimeException {

    public DuplicatedRepositoryException(String organization, String repository) {
        super(String.format("Organization %s Repository %s is not found", organization, repository));
    }
}
