package ru.vadim.naujavaprjct.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import ru.vadim.naujavaprjct.report.ReportStatus;

import java.time.OffsetDateTime;

@Entity
@Table (name = "report")
@NoArgsConstructor
public class Report {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "status")
    private String status;

    @Column(name = "body")
    private String body;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    public Report(String body, OffsetDateTime createdAt) {
        this.status = ReportStatus.CREATED.toString();
        this.body = body;
        this.createdAt = createdAt;
    }

    public Report(String status, String body, OffsetDateTime createdAt) {
        this.status = status;
        this.body = body;
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }
}


