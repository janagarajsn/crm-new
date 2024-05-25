package com.wnwa.crm.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int departmentId;
    @ManyToOne
    @JoinColumn(name = "stateId")
    private State state;
    
    
    @ManyToOne
    @JoinColumn(name = "countyId")
    private County county;

    @Column(unique=true)
    private String departmentName;
    @ManyToOne
    @JoinColumn(name = "statusId")
    private DepartmentStatus status;
    @Column(length = 5000)
    private String comments;
    private LocalDateTime reminderDate;
    private LocalDateTime creationDate;
    
    
    @PrePersist
    protected void onCreate(){
        creationDate = LocalDateTime.now();
    }
    
}