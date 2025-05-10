package org.example;

import java.time.LocalDate;
import java.util.Objects;

public abstract class AcademicItem implements Comparable<AcademicItem> {
    protected String title;
    protected String description;
    protected LocalDate createdDate;

    public AcademicItem(String title, String description) {
        this.title = title;
        this.description = description;
        this.createdDate = LocalDate.now();
    }

    /**
     * returns the type of the AcademicItem (Course, Assignment) as a string
     * @return string
     */
    public abstract String getType();

    /**
     * sorts AcademicItems according to createdDate
     * @param o the object to be compared.
     * @return the order of the objects
     */
    @Override
    public int compareTo(AcademicItem o) {
        return this.createdDate.compareTo(o.createdDate);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AcademicItem that = (AcademicItem) o;
        return Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(createdDate, that.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, createdDate);
    }

    @Override
    public String toString() {
        return "AcademicItem{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
