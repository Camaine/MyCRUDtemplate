package com.greenspring.green.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//ORM -> Java(다른언어) Object -> 테이블로 매핑해주는 기술
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto_increment
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이
    private String content;// Summer note Library<html>섞어서 디자인이 됨.터

    @ColumnDefault("0")
    private int count;//조회수

    @ManyToOne(fetch = FetchType.EAGER) // Many = Board, User = One
    @JoinColumn(name="userId")
    private User user;//DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.


    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) // mappedby 연관관계 주인공이 아님(FK가 아님)
    @JsonIgnoreProperties({"board"})
    @OrderBy("id desc")
    private List<Reply> reply;

    @CreationTimestamp
    private Timestamp createDate;
}
