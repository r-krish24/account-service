package com.maveric.accountservice.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {
    String code;
    String message;
}
