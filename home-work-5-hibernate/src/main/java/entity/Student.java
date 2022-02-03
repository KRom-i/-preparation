package entity;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Character mark;

    public Student () {
    }

    public Student (String name, Character mark) {
        this.name = name;
        this.mark = mark;
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public Character getMark () {
        return mark;
    }

    public void setMark (Character mark) {
        this.mark = mark;
    }

    @Override
    public String toString () {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mark=" + mark +
                '}';
    }
}
