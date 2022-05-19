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

    @Column(name = "rating")
    private Integer rating;

    public String getComment() {
        return comment;
    }

    public Integer getRating() {
        return rating;
    }

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "customer_id")
    private Long userId;

    public Comment(String comment, Integer rating, Boolean isActive, Long userId, Long filmId) {
        this.comment = comment;
        this.rating = rating;
        this.isActive = isActive;
        this.userId = userId;
        this.filmId = filmId;
    }
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "customer_id")
//    private User user;

    @Column(name = "film_id")
        private Long filmId;

    public Comment() {

    }

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "film_id")
//    private Film film;
}
