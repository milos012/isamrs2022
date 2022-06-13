package com.isamrs.backend.DTO;

import java.util.List;

import com.isamrs.backend.models.Authority;
import com.isamrs.backend.models.Boat;
import com.isamrs.backend.models.Cottage;
import com.isamrs.backend.models.InstructorOffer;
import com.isamrs.backend.models.Role;
import com.isamrs.backend.models.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String fname;
    private String lname;
    private String email;
    private String password;
    private Role role;

    private Authority authority;

    private List<Cottage> cottages;
    private List<Boat> boats;
    private List<InstructorOffer> instructorOffers;

    public UserDTO(User u){
        this.id = u.getId();
        this.fname = u.getFname();
        this.lname = u.getLname();
        this.email = u.getEmail();
        this.password = u.getPassword();
        this.role = u.getRole();

        this.authority = u.getAuthority();
        this.cottages = u.getCottages();
        this.boats = u.getBoats();
        this.instructorOffers = u.getInstructorOffers();
    }

    public UserDTO(Long id2, String fname2, String lname2, String email2, String password2, Role role2) {
        this.id = id2;
        this.fname = fname2;
        this.lname = lname2;
        this.email = email2;
        this.password = password2;
        this.role = role2;
    }

    public UserDTO(Long id2, String fname2, String lname2, String email2, String password2, Role role2, Authority aut, List<Cottage> cs, List<Boat> bs, List<InstructorOffer> is) {
        this.id = id2;
        this.fname = fname2;
        this.lname = lname2;
        this.email = email2;
        this.password = password2;
        this.role = role2;

        this.authority = aut;
        this.cottages = cs;
        this.boats = bs;
        this.instructorOffers =is;
    }


}
