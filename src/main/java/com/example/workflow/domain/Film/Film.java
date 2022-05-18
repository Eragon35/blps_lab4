package com.example.workflow.domain.Film;


import javax.persistence.*;

@Entity
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="token_seq")
    @SequenceGenerator(name="token_seq", sequenceName="token_seq", allocationSize=1)
    private Long id;

    public Long getId() {
        return id;
    }

    @Column(name = "name")
    private String name;
}
