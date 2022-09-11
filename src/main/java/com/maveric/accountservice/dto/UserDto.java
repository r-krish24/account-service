package com.maveric.accountservice.dto;

import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class UserDto {
    private String _id;
    private String firstName;
    private String email;
}
