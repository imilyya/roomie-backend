package com.roomie.backend.controller.dto;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserShortDto {
    private Long id;
    private String name;
    private String email;
    private String photoUrl;
}
