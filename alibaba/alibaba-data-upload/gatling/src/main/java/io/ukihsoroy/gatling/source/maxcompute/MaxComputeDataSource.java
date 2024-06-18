package io.ukihsoroy.gatling.source.maxcompute;

import com.aliyun.odps.jdbc.OdpsDriver;
import com.mysql.jdbc.ConnectionPropertiesImpl;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.naming.StringRefAddr;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * @author K.O
 */
public class MaxComputeDataSource extends ConnectionPropertiesImpl implements DataSource, Referenceable, Serializable {

    private String url;

    private String accessId;

    private String accessKey;

    private String project;

    protected static OdpsDriver odpsDriver;

    static {
        try {
            odpsDriver = new OdpsDriver();
        } catch (Exception E) {
            throw new RuntimeException("Can not load Driver class com.aliyun.odps.jdbc.OdpsDriver");
        }
    }

    /** Log stream */
    protected transient PrintWriter logWriter = null;

    /**
     * Default no-arg constructor for Serialization
     */
    public MaxComputeDataSource() {
    }

    /**
     * Creates a new connection using the already configured username and
     * password.
     *
     * @return a connection to the database
     *
     * @throws SQLException
     *             if an error occurs
     */
    @Override
    public java.sql.Connection getConnection() throws SQLException {
        return getConnection(this.project);
    }

    /**
     *
     * @param username accessId
     * @param password accessKey
     * @return jdbc conn
     * @throws SQLException
     */
    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        this.accessId = username;
        this.accessKey = password;
        return getConnection();
    }

    /**
     * Creates a new connection with the given username and password
     *
     * @param project
     *            the project to connect with
     *
     * @return a connection to the database
     *
     * @throws SQLException
     *             if an error occurs
     */
    public java.sql.Connection getConnection(String project) throws SQLException {
        return DriverManager.getConnection(String.format(url + "?project=%s", project), accessId, accessKey);
    }

    /**
     * Sets the log writer for this data source.
     *
     * @see javax.sql.DataSource#setLogWriter(PrintWriter)
     */
    @Override
    public void setLogWriter(PrintWriter output) throws SQLException {
        this.logWriter = output;
    }

    /**
     * Returns the log writer for this data source
     *
     * @return the log writer for this data source
     */
    @Override
    public java.io.PrintWriter getLogWriter() {
        return this.logWriter;
    }

    /**
     * @param seconds
     *
     * @throws SQLException
     */
    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
    }

    /**
     * Returns the login timeout
     *
     * @return the login timeout
     */
    @Override
    public int getLoginTimeout() {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Required method to support this class as a <CODE>Referenceable</CODE>.
     *
     * @return a Reference to this data source
     *
     * @throws NamingException
     *             if a JNDI error occurs
     */
    @Override
    public Reference getReference() throws NamingException {
        String factoryName = "io.ukihsoroy.gatling.source.odps.OdpsDataSourceFactory";
        Reference ref = new Reference(getClass().getName(), factoryName, null);
        ref.add(new StringRefAddr("accessId", accessId));
        ref.add(new StringRefAddr("accessKey", accessKey));
        ref.add(new StringRefAddr("project", project));

        //
        // Now store all of the 'non-standard' properties...
        //
        try {
            storeToRef(ref);
        } catch (SQLException sqlEx) {
            throw new NamingException(sqlEx.getMessage());
        }

        return ref;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}
