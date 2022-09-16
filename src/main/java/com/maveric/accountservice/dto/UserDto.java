package com.maveric.accountservice.dto;

import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String _id;
    private String firstName;
    private String email;
}
