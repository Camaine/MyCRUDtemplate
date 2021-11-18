package com.greenspring.green.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CharacterBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto_increment
    private int id;

    @Column(nullable = false, length = 100)
    private String ownerUid;

    @Column(nullable = false, length = 100)
    private String characterName;

    @Column(nullable = false, length = 100)
    private String creatorName;

    @Column(nullable = false, length = 30)
    private String spices;

    @Column(nullable = true, length = 30)
    private String primaryColor;

    @Column(nullable = true, length = 30)
    private String secondaryColor;

    @Column(nullable = true, length = 10)
    private int birthDay;

    @Column(nullable = true, length = 100)
    private String characteristic;

    @Column(nullable = true, length = 30)
    private String gender;

    @Column(nullable = true, length = 1000)
    private String bio;

    @Lob // 대용량 데이
    private String profileImage;// Summer note Library<html>섞어서 디자인이 됨.터

    private int count;//조회수

    @ManyToOne(fetch = FetchType.EAGER) // Many = Board, User = One
    @JoinColumn(name="userId")
    private TwtUser twtUser;//DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.

    @CreationTimestamp
    private Timestamp createDate;
}
