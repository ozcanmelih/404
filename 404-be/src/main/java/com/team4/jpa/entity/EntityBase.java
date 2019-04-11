package com.team4.jpa.entity;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class EntityBase {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @PrePersist
    public void onPrePersist() {
        this.setCreateDate(new Date());
    }
}
