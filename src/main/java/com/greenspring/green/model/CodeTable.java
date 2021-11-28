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
public class CodeTable {

    //User 많으면 Long 사용 가능
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto_increment
    private int id;

    @Column(nullable = false, length = 100)
    private String codeName; // 아이디

    @Column(nullable = false, length = 1000)
    private String KR;

    @Column(nullable = false, length = 1000)
    private String EN;

    @Column(nullable = false, length = 1000)
    private String JP;

}
