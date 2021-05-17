package com.epam.training.ticketservice.core.room.model;

import java.util.Objects;

public class RoomDto {
    private String name;
    private int seatRows;
    private int seatColumns;
    private int seatNumbers;

    public RoomDto(String name, int seatRows, int seatColumns) {
        this.name = name;
        this.seatRows = seatRows;
        this.seatColumns = seatColumns;
    }

    public int getSeatNumbers() {
        return seatColumns * seatRows;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeatRows() {
        return seatRows;
    }

    public void setSeatRows(Integer seatRows) {
        this.seatRows = seatRows;
    }

    public int getSeatColumns() {
        return seatColumns;
    }

    public void setSeatColumns(Integer seatColumns) {
        this.seatColumns = seatColumns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoomDto roomDto = (RoomDto) o;
        return Objects.equals(name, roomDto.name)
                && Objects.equals(seatRows, roomDto.seatRows)
                && Objects.equals(seatColumns, roomDto.seatColumns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, seatRows, seatColumns);
    }

    @Override
    public String toString() {
        return "\nRoom "
                + name
                + " with "
                + getSeatNumbers()
                + " seats, "
                + seatRows
                + " rows and "
                + seatColumns
                + " columns";
    }
}
