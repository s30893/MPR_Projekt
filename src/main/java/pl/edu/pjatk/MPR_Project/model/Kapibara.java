package pl.edu.pjatk.MPR_Project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity //1.
public class Kapibara {
    @Id//3.
    @GeneratedValue(strategy = GenerationType.AUTO)//4.
    private Long id;//2.
    private String name;
    private String color;

    public Kapibara(String name, String color) {
        this.name = name;
        this.color = color;
    }
    public Kapibara(){} //5.

    public Long getId() {//2a.
        return id;
    }

    public void setId(Long id) {//2b.
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
