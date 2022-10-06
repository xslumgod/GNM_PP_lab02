package tsn.jdbc.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class Launch {

    public static void main(String[] args) {
        // РАБОТА С БАЗОЙ ДАННЫХ MYSQL ЧЕРЕЗ JDBC
        try {
            // Адрес нашей базы данных "tsn_demo" на локальном компьютере (localhost)
            String url = "jdbc:mysql://localhost:3306/gnm_lab02?serverTimezone=Asia/Almaty&useSSL=false";

            // Создание свойств соединения с базой данных
            Properties authorization = new Properties();
            authorization.put("user", "root"); // Зададим имя пользователя БД
            authorization.put("password", "root"); // Зададим пароль доступа в БД

            // Создание соединения с базой данных
            Connection connection = DriverManager.getConnection(url, authorization);

            // Создание оператора доступа к базе данных
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            // Выполнение запроса к базе данных, получение набора данных
            
           int rows = 0;
            ResultSet table = statement.executeQuery("SELECT * FROM table1");
//            System.out.printf("Added %d rows", rows);
            //WHERE Фамилия like '%О%' and Отчество like '%Серг%'
            

            table.first(); // Выведем имена полей
            for (int j = 1; j <= table.getMetaData().getColumnCount(); j++) {
                System.out.print(table.getMetaData().getColumnName(j) + "\t\t");
            }
            System.out.println();

            table.beforeFirst(); // Выведем записи таблицы
            while (table.next()) {
                for (int j = 1; j <= table.getMetaData().getColumnCount(); j++) {
                    System.out.print(table.getString(j) + "\t\t");
                }
                System.out.println();
            }
            System.out.println();
            
            Scanner sc = new Scanner(System.in);
            
            System.out.println("Если вы хотите добавить строку введите 1");
            int adds = sc.nextInt();
            if (adds == 1) {
            System.out.println("Введите количество добовляемых строк");
            rows = sc.nextInt();
            
            for (int i=0; i<rows; i++){
            System.out.print("Surname - ");
            String surname = sc.next();
            System.out.println();
            System.out.print("Name - ");
            String name = sc.next();
            System.out.println();
            System.out.print("MiddleName - ");
            String middlename = sc.next();
            System.out.println();
            System.out.print("Address - ");
            String address = sc.next();
            System.out.println();
            System.out.print("Number - ");
            String number = sc.next();
            System.out.println();
            
            
            
            System.out.println("После добавления:");
            statement.execute("INSERT table1(Surname, Name, MiddleName, Address, Number) VALUES ('" + surname + "', '" + name + "', '" + middlename + "', '" + address + "', '" + number + "')");
            table = statement.executeQuery("SELECT * FROM table1");
            }
            System.out.printf("Added %d rows", rows);
            System.out.println();
            
            }
            while (table.next()) {
                for (int j = 1; j <= table.getMetaData().getColumnCount(); j++) {
                    System.out.print(table.getString(j) + "\t\t");
                }
                System.out.println();
            }
            
            System.out.println();
            System.out.println("Если вы хотите удалить строку введите 1");
            int delete = sc.nextInt();
            if (delete == 1) {
            System.out.println("Какую строку вы хотите удалить?");
            System.out.print("id - ");
            int scannedId = sc.nextInt();
            statement.execute("DELETE FROM table1 WHERE Id = " + scannedId);
            }
            
            System.out.println("После удаления:");
            table = statement.executeQuery("SELECT * FROM table1");
            while (table.next()) {
                for (int j = 1; j <= table.getMetaData().getColumnCount(); j++) {
                    System.out.print(table.getString(j) + "\t\t");
                }
                System.out.println();
            }
            
            
            System.out.println("Если вы хотите изменить строку введите 1");
            int change = sc.nextInt();
            if (change == 1) {
                System.out.println("Какую строку вы хотите изменить?");
            int Id = sc.nextInt();
            
            System.out.println("Введите изменяемые данные");
            
            System.out.print("Surname - ");
            String surname = sc.next();
//            System.out.println();
//            System.out.print("Name - ");
//            String name = sc.next();
//            System.out.println();
//            System.out.print("MiddleName - ");
//            String middlename = sc.next();
//            System.out.println();
//            System.out.print("Address - ");
//            String address = sc.next();
//            System.out.println();
//            System.out.print("Number - ");
//            String number = sc.next();
//            System.out.println();
            
            
                statement.executeUpdate("UPDATE table1 SET Surname = '" + surname + "' WHERE id = " + Id);
//              statement.executeUpdate("UPDATE my_books SET author = '" + scannedAuthorUp + "' WHERE id = " + Id);
            
            }
            
            System.out.println("После изменения:");
            table = statement.executeQuery("SELECT * FROM table1");
            while (table.next()) {
                for (int j = 1; j <= table.getMetaData().getColumnCount(); j++) {
                    System.out.print(table.getString(j) + "\t\t");
                }
                System.out.println();
            }
            
           Scanner sca = new Scanner(System.in);
            System.out.print("Введите условие (фильтрации) - (пример: Surname like '%Г%')");
            System.out.println();
            String scannedFilter = sca.nextLine();
            table = statement.executeQuery("SELECT * FROM table1 WHERE " + scannedFilter);
            while (table.next()) {
                for (int j = 1; j <= table.getMetaData().getColumnCount(); j++) {
                    System.out.print(table.getString(j) + "\t\t");
                }
                System.out.println();
            }
            
            if (table != null) { table.close(); } // Закрытие набора данных
            if (statement != null) { statement.close(); } // Закрытие базы данных
            if (connection != null) { connection.close(); } // Отключение от базы данных

        } catch (Exception e) {
            System.err.println("Error accessing database!");
            e.printStackTrace();
        }
    }

}
