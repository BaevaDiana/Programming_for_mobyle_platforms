package Examples;

public class TestEquals {
     public static void main(String[] args){
         MyDate date1 = new MyDate(7,11,2014);
         MyDate date2 = new MyDate(7,11,2014);

         if (date1 == date2) // проверка на равенство ссылок
             System.out.println("date1 is identifical date2");
         else
             System.out.println("date1 is not identifical date2");

         if (date1.equals(date2)) // проверка на равенство объектов
             System.out.println("date1 is equal date2");
         else
             System.out.println("date1 is not equal date2");

         System.out.println("set date2=date1");
         date2 = date1; // устанавливаем равенство ссылок

         if(date1==date2)
             System.out.println("date1 is equal date2");
         else
             System.out.println("date1 is not equal date2");

     }
}
