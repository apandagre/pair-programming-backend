package com.pairprogrmmingbackend.pairprogramming.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RoomDto {
    private Long roomId;

    private String name;

    private String language;

    private UserDto owner;

    private String code;

    private Integer members;
}

