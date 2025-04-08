package com.example.project.infrastructure.entity;

import com.example.project.domain.model.Project;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "project")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(name = "start_date")
    private LocalDate startDate;


    @Column(name = "end_date")
    private LocalDate endDate;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TaskEntity> tasks;

    public static ProjectEntity from(Project project) {
        var entity = new ProjectEntity();
        entity.setId(project.id());
        entity.setName(project.name());
        entity.setStartDate(project.startDate());
        entity.setEndDate(project.endDate());
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskEntity> tasks) {
        this.tasks = tasks;
    }
}
