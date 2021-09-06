package com.greenspring.green.dto;

import org.springframework.http.HttpStatus;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDTO<T> {
	HttpStatus status;
	T data;
}
