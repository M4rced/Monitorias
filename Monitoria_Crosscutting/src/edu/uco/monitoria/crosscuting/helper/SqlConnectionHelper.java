package edu.uco.monitoria.crosscuting.helper;

import edu.uco.monitoria.crosscuting.exception.crosscutting.CrosscutingCustomException;
import edu.uco.monitoria.crosscuting.messages.Messages;

import java.sql.Connection;
import java.sql.SQLException;

public class SqlConnectionHelper {
	static Exception exception = new Exception();
    private SqlConnectionHelper(){
        super();
    }


    public static final boolean connectionIsNull(final Connection connection){
        return ObjectHelper.isNull(connection);
    }

    public static final boolean connectionIsOpen(final Connection connection){
        try {
            return !connectionIsNull(connection) && !connection.isClosed();
        } catch (final SQLException exception){
            throw new CrosscutingCustomException(Messages.SqlConnectionHelper.CONNECTION_IS_CLOSED, exception.getMessage(), (Exception) exception.getCause());
        }
    }

    public static final void closeConnection(final Connection connection){
        try {
            if (!connectionIsOpen(connection)) {
                Exception exception = new Exception();
                throw CrosscutingCustomException.createTechnicalException(Messages.SqlConnectionHelper.CONNECTION_IS_ALREADY_CLOSED, (Exception) exception.getCause());
            }
            connection.close();
        } catch (final SQLException exception){
            throw CrosscutingCustomException.createTechnicalException(Messages.SqlConnectionHelper.PROBLEM_TRYING_TO_CLOSE_THE_CONNECTION,(Exception) exception.getCause());
        }
    }
public static final void innitTransaction(final Connection connection){
        try {
            if (!connectionIsOpen(connection)) {
                throw CrosscutingCustomException.createTechnicalException(Messages.SqlConnectionHelper.CONNECTION_IS_CLOSED_FOR_INIT_TRANSACTION, (Exception) exception.getCause());
            }
            connection.setAutoCommit(false);
        } catch (final SQLException exception){
            throw CrosscutingCustomException.createTechnicalException(Messages.SqlConnectionHelper.PROBLEM_TRYING_TO_INIT_TRANSACTION,(Exception) exception.getCause());
        }
    }

    public static final void commitTransaction(final Connection connection){
        try {
            if (!connectionIsOpen(connection)) {
                throw CrosscutingCustomException.createTechnicalException(Messages.SqlConnectionHelper.CONNECTION_IS_CLOSED_FOR_COMMIT_TRANSACTION,(Exception) exception.getCause());
            }
            connection.setAutoCommit(false);
        } catch (final SQLException exception){
            throw CrosscutingCustomException.createTechnicalException(Messages.SqlConnectionHelper.PROBLEM_TRYING_TO_COMMIT_TRANSACTION,(Exception) exception.getCause());
        }
    }

    public static final void rollbackTransaction(final Connection connection){
        try {
            if (!connectionIsOpen(connection)) {
                throw CrosscutingCustomException.createTechnicalException(Messages.SqlConnectionHelper.CONNECTION_IS_CLOSED_FOR_ROLLBACK_TRANSACTION,(Exception) exception.getCause());
            }
            connection.rollback();
        } catch (final SQLException exception){
            throw CrosscutingCustomException.createTechnicalException(Messages.SqlConnectionHelper.PROBLEM_TRYING_TO_ROLLBACK_TRANSACTION,(Exception) exception.getCause());
        }
    }
}
