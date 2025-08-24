package ru.baskakov;

public class Contact {
    private String name;
    private String phone;
    private String email;
    private String group;

    public Contact(String name, String phone, String email, String group) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Contact other = (Contact) obj;

        return (phone.equals(other.phone)) && (email.equals(other.email));
    }

    @Override
    public int hashCode() {
        return 31 * (phone.hashCode() + email.hashCode());
    }

    @Override
    public String toString() {
        return String.format(" %s | %s | %s ", name, phone, email);
    }
}
