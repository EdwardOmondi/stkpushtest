package me.edward.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdDate", "editedDate", "createdBy", "editedBy", "deletedBy", "deletedDate", "active"}, allowGetters = true)
public class AbstractEntity implements Serializable {

    @Id
    @JsonProperty("id")
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("ref")
    @Column(name = "ref", unique = true, nullable = false, length = 100)
    private String ref;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdDate;

    @JsonProperty("createdBy")
    @Column(name = "createdBy")
    @JsonIgnore
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "editedDate", nullable = true)
    @LastModifiedDate
    @JsonIgnore
    private Date editedDate;

    @JsonProperty("editedBy")
    @Column(name = "editedBy")
    @JsonIgnore
    private String editedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deletedDate", nullable = true)
    @LastModifiedDate
    @JsonIgnore
    private Date deletedDate;

    @JsonProperty("deletedBy")
    @Column(name = "deletedBy")
    @JsonIgnore
    private String deletedBy;

    @JsonProperty("deleted")
    @Column(name = "deleted", nullable = false)
    @JsonIgnore
    private boolean deleted;

    @JsonProperty("active")
    @Column(name = "active", nullable = false)
    @JsonIgnore
    private boolean active = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getEditedDate() {
        return editedDate;
    }

    public void setEditedDate(Date editedDate) {
        this.editedDate = editedDate;
    }

    public String getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(String editedBy) {
        this.editedBy = editedBy;
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
