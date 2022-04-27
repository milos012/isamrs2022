package com.isamrs.backend.models;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "instructoroffer")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InstructorOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String offerName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String profileImage;

    @Column(nullable = false)
    private Double pricePerHr;

    @ManyToOne
    private User user;
    
}
