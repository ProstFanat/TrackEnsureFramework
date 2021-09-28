package DB.entity.DAO;

import DB.DBConnection;
import DB.entity.EldTransactionHistory;
import DB.entity.EldTransactionRejectComment;
import javafx.beans.binding.BooleanBinding;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EldTransactionRejectCommentDAO {
    public String db;
    public String user;
    public String pass;

    public EldTransactionRejectCommentDAO(String db, String user, String pass) {
        this.db = db;
        this.user = user;
        this.pass = pass;
    }

    public Boolean isRepeatedIssueByTransactionId(int transactionId){
        EldTransactionRejectComment vo = null;
        boolean result = false;

        Connection connection = DBConnection.getConnection(db, user, pass);
        String sql = "SELECT et.transaction_id, et.record_id, etrc.is_repeated_issue FROM eld.eld_transaction_history et JOIN eld.eld_transaction_reject_comment etrc ON et.record_id = etrc.transaction_history_record_id WHERE transaction_id = " + transactionId + ";";
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                vo = new EldTransactionRejectComment(rs, null);
                result = vo.getIsRepeatedIssue() == 'Y';
                System.out.println(result);
                System.out.println(vo.getIsRepeatedIssue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
