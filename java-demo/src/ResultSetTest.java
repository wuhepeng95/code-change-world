import java.sql.*;

public class ResultSetTest {

    // 定义数据连接的驱动、连接、用户名、密码
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://106.15.93.42:3306/whptest";
    private static final String username = "root";
    private static final String password = "123456";

    // 定义会使用的jdbc对象，方便释放资源
    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    // 获取数据库连接
    public static Connection getConnection() {
        // 加
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 连
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        // 获取连接实例
        try {
            connection = getConnection();
            assert connection != null;
            // 预编译对象防止sql注入
            preparedStatement = connection.prepareStatement("select * from whp_test where id < 10");
            // 获取ResetSet
            resultSet = preparedStatement.executeQuery();
            // 取出列名
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(metaData.getColumnName(i) + "\t");
            }
            System.out.println();
            // 相当于游标一样，没次取得都是一条
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + "\t");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //  释放资源
            try {
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }
}
