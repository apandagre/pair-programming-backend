package com.pairprogrmmingbackend.pairprogramming.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn()
    private User owner;

    @Column()
    private String code;

    @Column()
    private Integer members = 0;

    @Column()
    private String language;

}
