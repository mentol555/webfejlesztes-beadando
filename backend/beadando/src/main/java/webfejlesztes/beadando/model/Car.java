package webfejlesztes.beadando.model;

import javax.persistence.*;

@Entity
@Table(name = "autok")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "marka")
    private String name;

    @Column(name = "szin")
    private String color;

    @Column(name = "gyartasi_ev")
    private String manYear;

    @Column(name = "rendszam")
    private String nr;

    public Car() {}

    public Car(String name, String color, String manYear, String nr) {
        this.name = name;
        this.color = color;
        this.manYear = manYear;
        this.nr = nr;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getManYear() {
        return manYear;
    }

    public void setManYear(String manYear) {
        this.manYear = manYear;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }
}
