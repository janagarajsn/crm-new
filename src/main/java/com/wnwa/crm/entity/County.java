package com.wnwa.crm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class County {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer countyId;
    @ManyToOne
    @JoinColumn(name = "stateId")
    private State state;
    private String countyName;
}
