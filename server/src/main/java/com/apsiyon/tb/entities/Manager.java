package com.apsiyon.tb.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "managers")
public class Manager  extends AppUser{

    private String fullName;

    @OneToMany(mappedBy = "manager")
    private List<Request> requests;




}
