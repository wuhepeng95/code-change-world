package i.am.whp.handler;

import i.am.whp.model.enums.Status;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusHandler extends BaseTypeHandler<Status> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Status status, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, status.getValue());
    }

    @Override
    public Status getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return Status.valueOf(resultSet.getInt(s));
    }

    @Override
    public Status getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public Status getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
