package com.epam.training.ticketservice.core.room.persistance.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
    private String name;
    private int rows;
    private int columns;

    public Room() {
    }

    public Room(Integer id, String name, int rows, int columns) {
        this.id = id;
        this.name = name;
        this.rows = rows;
        this.columns = columns;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Room room = (Room) o;
        return Double.compare(room.rows, rows) == 0
                && Double.compare(room.columns, columns) == 0
                && Objects.equals(id, room.id) && Objects.equals(name, room.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rows, columns);
    }

    @Override
    public String toString() {
        return "Room{"
                + "id="
                + id
                + ", name='"
                + name
                + '\''
                + ", rows="
                + rows
                + ", columns="
                + columns
                + '}';
    }
}
