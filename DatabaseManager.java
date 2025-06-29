import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DatabaseManager {

    public static void saveScore(String playerName, int score) {
    String url = "jdbc:mysql://localhost:3306/car_game";
    String user = "root";
    String password = "Your Password";

    String query = "INSERT INTO scores (player_name, score) VALUES (?, ?)";

    try {
        Class.forName("com.mysql.cj.jdbc.Driver"); 
        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, playerName);
        stmt.setInt(2, score);
        stmt.executeUpdate();
        System.out.println("Score saved successfully!");
        conn.close();
    } catch (Exception e) {
        System.out.println("Failed to save score.");
        e.printStackTrace();
    }
}
}
