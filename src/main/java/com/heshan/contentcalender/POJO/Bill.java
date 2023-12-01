package com.heshan.contentcalender.POJO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
@NamedQuery(name="Bill.getBillById",query = "select b from Bill b where b.id=:id")
@NamedQuery(name="Bill.getBillsByUsername",query = "select b from Bill b where b.createdBy=:username")
@NamedQuery(name="Bill.getBillsByUUID",query = "select b from Bill b where b.uuid=:uuid")
@NamedQuery(name="Bill.getBillByUID",query = "select b from Bill b where b.uuid=:uuid")

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table(name = "bill")
public class Bill implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String uuid;
    private String name;
    private String email;
    private String contactNumber;
    private String paymentMethod;
    private String total;
    @Column(columnDefinition = "JSON")
    private String productDetails;
    private String createdBy;

}
