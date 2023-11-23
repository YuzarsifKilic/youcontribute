package com.yuzarsif.youcontribute.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IssueChallenge {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(strategy = "native", name = "native")
    private Integer id;
    @OneToOne
    @JsonManagedReference
    private Issue issue;
    @Enumerated(EnumType.STRING)
    private IssueChallengeStatus status;
    @CreationTimestamp
    private Date createdAt;
    private Date updateAt;
}
