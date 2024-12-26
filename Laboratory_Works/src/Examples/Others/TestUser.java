package Examples.Others;

public class TestUser
{
    public static void main(String[] args) {
            User us1 = new User ("Иван", "Петров", 1996);
            System.out.println(us1);
            System.out.println(us1.toString());
            System.out.println("Hach:"+ us1.hashCode());
    }
}
