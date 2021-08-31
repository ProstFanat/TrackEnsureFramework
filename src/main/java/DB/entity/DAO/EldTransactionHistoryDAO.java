package DB.entity.DAO;

import DB.DBConnection;
import DB.entity.EldTransactionHistory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EldTransactionHistoryDAO {
    public String db;
    public String user;
    public String pass;

    public EldTransactionHistoryDAO(String db, String user, String pass) {
        this.db = db;
        this.user = user;
        this.pass = pass;
    }

    public EldTransactionHistory getTransactionRecordId(int transactionId){
        EldTransactionHistory vo = null;

        Connection connection = DBConnection.getConnection(db, user, pass);
        String sql = "SELECT * FROM acl_user WHERE user_id IN " +
                "(SELECT acl_user_id FROM fleet.driver_profile WHERE driver_id = " + transactionId + ")";
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                vo = new EldTransactionHistory(rs, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }
}
