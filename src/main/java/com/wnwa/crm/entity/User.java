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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @ManyToOne
    @JoinColumn(name = "stateId")
    private State state;

    @ManyToOne
    @JoinColumn(name = "countyId")
    private County county;

    @ManyToOne
    @JoinColumn(name = "departmentId")
    private Department department;

    private String userName;
    private String emailId;

}
