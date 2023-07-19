package com.pairprogrmmingbackend.pairprogramming.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class RoomCreationRequest {
    private String name;
    private String language;
}
