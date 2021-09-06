package com.greenspring.green.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴
@Entity //User Class가 MySQL에 생성
// @DynamicInsert // insert 시에 null인 필드 제외
public class User {

    //User 많으면 Long 사용 가능
    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)//프로젝트에서 연결된 DB의 넘버링 전략을 따라감
    private int id; // squence, auto_increment

    @Column(nullable = false, length = 30, unique = true)
    private String username; // 아이디

    @Column(nullable = false, length = 100) // 해쉬(비밀번호 암호화)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @CreationTimestamp //시간이 자동입력
    private Timestamp createDate;

    @CreationTimestamp
    private Timestamp updateDate;

    //In DB there no type'RoleType'
    @Enumerated(EnumType.STRING)
    private RoleType role; //admin, user


}
