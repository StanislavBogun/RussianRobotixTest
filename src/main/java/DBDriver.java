import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

class DBDriver{
    private static DBDriver instance;
    private DBDriver(){}
    public static synchronized DBDriver getInstance(){
        if(instance == null){
            instance = new DBDriver();
        }
        return instance;
    }

    private Connection conn;
    public void Conn() throws ClassNotFoundException, SQLException{
        Class.forName("org.sqlite.JDBC");
        this.conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\koytt\\IdeaProjects\\Robotics\\src\\main\\resources\\Robotics.db");
        System.out.println("База Подключена!");
    }

    public void WriteDB(List<Report> Report, String vendor) throws SQLException {
        Statement statmt = this.conn.createStatement();
        Report.forEach(it -> {
            try {
                statmt.execute("INSERT INTO 'PriceItems ' ('Vendor', 'Number','SearchVendor','SearchNumber','Description','Price','Count')" +
                        " VALUES ("+ it.getVendorCode() +", " + it.getCatalogueNumber() + ","+ vendor + ", "+ it.getCatalogueNumber() +"," + it.getDescription() +
                        ", "+ it.getPrice() +", "+ it.getApplicability() +" ); "
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });


        System.out.println("Таблица заполнена");
    }
}
