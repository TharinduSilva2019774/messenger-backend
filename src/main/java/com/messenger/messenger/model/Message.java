package com.messenger.messenger.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "massages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "messageBody")
    private String messageBody;

    @Column(name = "time")
    @CreationTimestamp
    private Timestamp time;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;

}
