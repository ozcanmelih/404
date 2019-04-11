package com.team4.jpa.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CANDIDATE")
public class Candidate extends EntityBase {

    private String name;
    private String surname;
    private Long jiraId;
    private String photoUrl;

    @OneToMany(mappedBy = "candidate")
    private List<EventLog> eventLogs;

    public Candidate() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getJiraId() {
        return jiraId;
    }

    public void setJiraId(Long jiraId) {
        this.jiraId = jiraId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
