package web4.models;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "point")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "xVal", nullable = false)
    private Double x;
    @Column(name = "yVal", nullable = false)
    private Double y;
    @Column(name = "rVal", nullable = false)
    private Double r;
    @Column(name = "res", nullable = false)
    private String result;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public Point() {
    }

    public Point(Double x, Double y, Double r, String result, User user) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = result;
        this.author = user;
    }

    @Override
    public String toString() {
        return "Point{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", result='" + result + '\'' +
                '}';
    }
}
