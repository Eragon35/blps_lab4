package com.example.workflow.domain.Comment;


import com.example.workflow.domain.Film.Film;
import com.example.workflow.domain.User.User;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="token_seq")
    @SequenceGenerator(name="token_seq", sequenceName="token_seq", allocationSize=1)
    private Long id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "is_active")
    private boolean isActive;

//    @Column(name = "customer_id")
    //private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private User userId;

//    @Column(name = "film_id")
//        private Long filmId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id")
    private Film film;
}
