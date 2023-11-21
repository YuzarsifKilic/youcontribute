package com.yuzarsif.youcontribute.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name  = "native")
    private Integer id;
    private Long githubIssuesId;
    private Integer githubIssueNumber;
    private String title;
    @Column(columnDefinition = "text")
    private String body;

    private String url;

    @ManyToOne
    @JoinColumn(name = "repository_id")
    @JsonManagedReference
    private Repository repository;
}
