package DB.entity;

import DB.RecordMapperValueObject;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.List;
import java.util.Set;

public class EldTransactionRejectComment extends RecordMapperValueObject {
    public Character isRepeatedIssue;

    public EldTransactionRejectComment(final ResultSet rs, Set<String> rsColumns) throws Exception {
        if (rsColumns == null) {
            rsColumns = getRSColumns(rs);
        }
        this.isRepeatedIssue = getCharacter(rs, "is_repeated_issue", rsColumns);
    }

    public Character getIsRepeatedIssue() {
        return isRepeatedIssue;
    }

    public void setIsRepeatedIssue(Character isRepeatedIssue) {
        this.isRepeatedIssue = isRepeatedIssue;
    }

    @Override
    public List<Field> getFields() {
        return null;
    }
}
