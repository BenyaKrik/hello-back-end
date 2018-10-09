package com.example.maslo.hellobackend.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "contacts")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Contact implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

}
