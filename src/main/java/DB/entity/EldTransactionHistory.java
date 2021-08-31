package DB.entity;

import DB.RecordMapperValueObject;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.List;
import java.util.Set;

import static DB.RecordMapperValueObject.getRSColumns;

public class EldTransactionHistory extends RecordMapperValueObject {
    public Integer recordId;
    public Long transactionId;

    public EldTransactionHistory(final ResultSet rs, Set<String> rsColumns) throws Exception {
        if (rsColumns == null) {
            rsColumns = getRSColumns(rs);
        }
        this.recordId = getInteger(rs, "record_id", rsColumns);
        this.transactionId = getLong(rs, "transaction_id", rsColumns);
    }


    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public List<Field> getFields() {
        return null;
    }
}
