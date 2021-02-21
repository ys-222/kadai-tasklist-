package models;

import java.security.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tasklist")

public class Task {
    @Id
    @Column(name = "id") // タスクID
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主キー値を自動採番
    private Integer id;
    
    @Column(name = "title", length = 20, nullable = false) // タスク名
    private String title;
    
    @Column(name = "content", length = 100, nullable = false) //タスク内容
    private String content;
    
    @Column(name = "created_at", nullable = false) //作成日時
    private Timestamp created_at;
    
    @Column(name = "updated_at", nullable = false) //更新日時
    private Timestamp updated_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
    
}
