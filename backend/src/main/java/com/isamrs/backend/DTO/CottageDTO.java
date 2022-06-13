package com.isamrs.backend.DTO;

import com.isamrs.backend.models.Cottage;
import com.isamrs.backend.models.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CottageDTO {

    private Long id;

    private String name;
    private String address;
    private String description;
    private String profileImage;
    private Double price;

    //
    private User user;
    
    public CottageDTO(Cottage c){
        this.id = c.getId();
        this.name = c.getName();
        this.address = c.getAddress();
        this.description = c.getDescription();
        this.profileImage = c.getProfileImage();
        this.price = c.getPrice();

        this.user = c.getUser();
        
    }

    public CottageDTO(Long id2, String name2, String addres2, String description2, String profileimg, Double price){
        this.id = id2;
        this.name = name2;
        this.address = addres2;
        this.description = description2;
        this.profileImage = profileimg;
        this.price = price;

    }
    public CottageDTO(Long id2, String name2, String addres2, String description2, String profileimg, Double price, User u){
        this.id = id2;
        this.name = name2;
        this.address = addres2;
        this.description = description2;
        this.profileImage = profileimg;
        this.price = price;
        this.user = u;

    }
}
