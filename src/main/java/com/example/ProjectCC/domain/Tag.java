package com.example.ProjectCC.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;

    private String userId;

    private String tag;

}