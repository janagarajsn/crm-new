package com.wnwa.crm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "county", uniqueConstraints = {@UniqueConstraint(columnNames = {"stateId", "countyId"})})
public class County {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer countyId;
    @ManyToOne
    @JoinColumn(name = "stateId", nullable = false)
    private State state;
    private String countyName;
}
