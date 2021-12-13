package web4.models;


import javax.persistence.*;

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

    public Point() {
    }

    public Point(Double x, Double y, Double r, String result) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id")
//    private User author;

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
