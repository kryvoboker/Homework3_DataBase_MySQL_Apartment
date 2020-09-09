import java.sql.*;
import java.util.Scanner;

public class Main {

    private static final String DB_CONNECTION = "jdbc:mysql://localhost/testBaseTwo?useUnicode=true&serverTimezone=UTC&" +
            "useSSL=true&verifyServerCertificate=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    private static Connection conn;
    private static PreparedStatement ps;

    public static void main(String[] args) throws SQLException {

        try {
            conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            changeApartment();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        } finally {
            if (conn != null) conn.close();
        }
    }

    private static void changeApartment() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter parametr");
        System.out.println("Please enter value");
        String parametr = sc.nextLine();
        String value = sc.nextLine();
        if (parametr.equals("id")) {
            int id = Integer.parseInt(value);
            ps = conn.prepareStatement("select * from Clients where id = ?");
            ps.setInt(1, id);
        } else if (parametr.equals("district")) {
            ps = conn.prepareStatement("select * from Clients where district = ?");
            ps.setString(1, value);
        } else if (parametr.equals("adress")) {
            ps = conn.prepareStatement("select * from Clients where adress = ?");
            ps.setString(1, value);
        } else if (parametr.equals("area")) {
            double area = Double.parseDouble(value);
            ps = conn.prepareStatement("select * from Clients where area = ?");
            ps.setDouble(1, area);
        } else if (parametr.equals("rooms")) {
            int rooms = Integer.parseInt(value);
            ps = conn.prepareStatement("select * from Clients where rooms = ?");
            ps.setInt(1, rooms);
        } else if (parametr.equals("price")) {
            int price = Integer.parseInt(value);
            ps = conn.prepareStatement("select * from Clients where price = ?");
            ps.setInt(1, price);
        }
        try {
            ResultSet rs = ps.executeQuery();
            try {
                ResultSetMetaData md = rs.getMetaData();

                for (int i = 1; i <= md.getColumnCount(); i++)
                    System.out.print(md.getColumnName(i) + "\t\t");
                System.out.println();

                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t\t");
                    }
                    System.out.println();
                }
            } finally {
                rs.close();
            }
        } finally {
            ps.close();
        }
    }
}
