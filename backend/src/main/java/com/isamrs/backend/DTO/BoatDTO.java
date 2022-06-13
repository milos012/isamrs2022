package com.isamrs.backend.DTO;

import com.isamrs.backend.models.Boat;
import com.isamrs.backend.models.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoatDTO {

    private Long id;
    private String name;
    private String description;
    private String profileImage;
    private Double pricePerHr;
    private boolean driver;

    private User user;

    public BoatDTO(Boat b){
        this.id = b.getId();
        this.name = b.getName();
        this.description = b.getDescription();
        this.profileImage = b.getProfileImage();
        this.pricePerHr = b.getPricePerHr();
        this.driver = b.isDriver();

        this.user = b.getUser();
        
    }

    public BoatDTO(Long id2, String name2, String description2, String profileimg, Double priceHr, boolean driver2){
        this.id = id2;
        this.name = name2;
        this.description = description2;
        this.profileImage = profileimg;
        this.pricePerHr = priceHr;
        this.driver = driver2;

    }

    public BoatDTO(Long id2, String name2, String description2, String profileimg, Double priceHr, boolean driver2, User us){
        this.id = id2;
        this.name = name2;
        this.description = description2;
        this.profileImage = profileimg;
        this.pricePerHr = priceHr;
        this.driver = driver2;
        this.user = us;

    }
    
}
