package com.mycompany.myapp.domain.auto;

import java.time.LocalDate;

/**
 * Alert
 */
public class Alert {

    private String name;

    private String details;

    private LocalDate expirationDate;

    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Alert(String name, String details, LocalDate expirationDate, String type) {
        this.name = name;
        this.details = details;
        this.expirationDate = expirationDate;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alert)) return false;

        Alert alert = (Alert) o;

        if (name != null ? !name.equals(alert.name) : alert.name != null) return false;
        return expirationDate != null ? expirationDate.equals(alert.expirationDate) : alert.expirationDate == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (expirationDate != null ? expirationDate.hashCode() : 0);
        return result;
    }
}
