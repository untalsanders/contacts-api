package io.github.untalsanders.contacts.shared.domain;

import lombok.Data;

import java.util.List;

@Data
public class ApiResponse<T> {
    private Boolean success;
    private String message;
    private T data;
    private List<String> errors;
    private Integer errorCode;
    private Long timestamp;
    private String path;
}
