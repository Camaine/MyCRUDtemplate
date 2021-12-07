package com.greenspring.green.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CharacterLikeLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto_increment
    private int id;

    @Column
    private int characterId;

    @Column
    private int status;

    @Column(nullable = false, length = 100)
    private String uid;

    @CreationTimestamp
    private Timestamp createDate;
}
