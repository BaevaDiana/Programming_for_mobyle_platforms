
public class User
{
    private String name;
    private String surname;
    private int birthYear;

    public User(String name, String surname, int birthYear)
    {
        this.name = name;
        this.surname = surname;
        this.birthYear = birthYear;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getName() {
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public int getBirthYear() {
        return birthYear;
    }

    @Override
    public String toString() {
        return this.name +" "+ this.surname + " " + getBirthYear()+"г.";
    }
}




