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
public class ServiceLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto_increment
    private int id;

    @Column(nullable = false, length = 100)
    private String apiCode;

    @Column(nullable = false, length = 100)
    private String uid;

    @Column(nullable = false, length = 1000)
    private String reqInfo;

    @Column(nullable = false)
    private int result;

    @CreationTimestamp
    private Timestamp createDate;
}
