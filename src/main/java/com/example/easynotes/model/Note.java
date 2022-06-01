package com.example.easynotes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Entity // persistent java class
@Table(name = "notes")
// createdAt and updatedAt fields should automatically get populated whenever we create or update an entity
@EntityListeners(AuditingEntityListener.class)
// don’t want the clients of the rest api to supply the createdAt and updatedAt values
// include these values in the JSON response
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class Note implements Serializable {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK generation strategy: auto-increment
    private Long id;

    @NotBlank // not null
    private String title;

    @NotBlank // not null
    private String content;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP) // converts time/date object to compatible db type
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP) // converts time/date object to compatible db type
    @LastModifiedDate
    private Date updatedAt;

    public Note(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
