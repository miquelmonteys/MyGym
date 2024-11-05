package com.mygym.security;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;


import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class Auditable {

    @CreatedBy
    // @JsonIgnore
    protected String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    // @JsonIgnore
    protected Date createdDate;

    @LastModifiedBy
    // @JsonIgnore
    protected String lastModifiedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    // @JsonIgnore
    protected Date lastModifiedDate;
}