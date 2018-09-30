package ua.skillsup.isut.unit_test;

import java.time.LocalDate;
import java.util.Objects;

public class ExampleEntity {
    private final long id;
    private final String name;
    private final LocalDate dateBirth;

    public ExampleEntity(long id, String name, LocalDate dateBirth) {
        this.id = id;
        this.name = name;
        this.dateBirth = dateBirth;
    }

    @Override
    public String toString() {
        return "ExampleEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateBirth=" + dateBirth +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExampleEntity)) return false;
        ExampleEntity that = (ExampleEntity) o;
        return id == that.id &&
                name.equals(that.name) &&
                Objects.equals(dateBirth, that.dateBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateBirth);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }
}
