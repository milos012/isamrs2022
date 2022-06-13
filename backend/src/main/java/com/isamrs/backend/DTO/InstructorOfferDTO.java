package com.isamrs.backend.DTO;

import com.isamrs.backend.models.InstructorOffer;
import com.isamrs.backend.models.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InstructorOfferDTO {

    private Long id;
    private String offerName;
    private String description;
    private String profileImage;
    private Double pricePerHr;

    private User user;

    public InstructorOfferDTO(InstructorOffer io){
        this.id = io.getId();
        this.offerName = io.getOfferName();
        this.description = io.getDescription();
        this.profileImage = io.getProfileImage();
        this.pricePerHr = io.getPricePerHr();

        this.user = io.getUser();
        
    }

    public InstructorOfferDTO(Long id2, String name2, String description2, String profileimg, Double price){
        this.id = id2;
        this.offerName = name2;
        this.description = description2;
        this.profileImage = profileimg;
        this.pricePerHr = price;

    }
    public InstructorOfferDTO(Long id2, String name2, String description2, String profileimg, Double price, User u){
        this.id = id2;
        this.offerName = name2;
        this.description = description2;
        this.profileImage = profileimg;
        this.pricePerHr = price;
        this.user = u;

    }
    
}
