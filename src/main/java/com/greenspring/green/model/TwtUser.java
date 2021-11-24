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
@Builder //빌더 패턴
@Entity //User Class가 MySQL에 생성
// @DynamicInsert // insert 시에 null인 필드 제외
public class TwtUser {

    //User 많으면 Long 사용 가능
    @Id //Primary Key
    @Column(nullable = false, length = 100, unique = true)
    private String uid; // squence, auto_increment

    @Column(nullable = false, length = 100)
    private String screenName; // 아이디

    @Column(nullable = false, length = 100)
    private String displayName;

    @Column(nullable = false)
    private int characterCnt;

    @Column(nullable = false, length = 1000)
    private String photoURL;

    @CreationTimestamp //시간이 자동입력
    private Timestamp createDate;

    @CreationTimestamp
    private Timestamp updateDate;

    //In DB there no type'RoleType'
    @Column(nullable = false, length = 255)
    private String role; //admin, user

    @Column(nullable = false, length = 255)
    private String lang;


}
