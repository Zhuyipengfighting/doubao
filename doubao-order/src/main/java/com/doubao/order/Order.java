package com.doubao.order;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sys_order")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Double totalAmount;

    @Column(nullable = false)
    private Integer status;

    private String paymentMethod;

    private Date paymentTime;

    private Date cancelTime;

    @Column(updatable = false)
    private Date createTime;

    private Date updateTime;
}