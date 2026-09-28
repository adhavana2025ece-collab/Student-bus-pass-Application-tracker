package com.buspass.studentbuspass.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bus_routes")
public class BusRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String routeName;

    @Column(nullable = false)
    private String source;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private String boardingPoint;

    @OneToMany(mappedBy = "route")
    @JsonIgnore
    private List<BusPassApplication> applications = new ArrayList<>();

    public BusRoute() {
    }

    public BusRoute(String routeName, String source,
                    String destination, String boardingPoint) {
        this.routeName = routeName;
        this.source = source;
        this.destination = destination;
        this.boardingPoint = boardingPoint;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getBoardingPoint() {
        return boardingPoint;
    }

    public void setBoardingPoint(String boardingPoint) {
        this.boardingPoint = boardingPoint;
    }

    public List<BusPassApplication> getApplications() {
        return applications;
    }

    public void setApplications(List<BusPassApplication> applications) {
        this.applications = applications;
    }
}