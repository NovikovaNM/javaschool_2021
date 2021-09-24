
package ru.homework.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SampleDAO {

    private static final Logger log = LoggerFactory.getLogger(SampleDAO.class);

    private Connection conn;

    /**
     * Assign the connection to use for this DAO
     *
     * @param conn
     */
    public void setConn(Connection conn) {
        this.conn = conn;
    }

    /**
     * Create a table in the database
     */
    boolean success = false;
    public boolean createTable() {
        if (conn != null) {
            Statement stmt = null;

            try {
                stmt = conn.createStatement();
                stmt.execute("CREATE TABLE sample_table (id INT IDENTITY, first_name VARCHAR(30), last_name VARCHAR(30), age INT)");
                log.info("Creating sample_table");
                success = true;
            } catch (SQLException e) {
                log.error("Unable to create the database table", e);
            } finally {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException e) {
                    }
                }
            }
        }
        return success;
    }

    /**
     * Remove our sample table
     *
     * @return
     */
    public boolean dropTable() {
        boolean success = false;
        if (conn != null) {
            Statement stmt = null;

            try {
                stmt = conn.createStatement();
                stmt.execute("DROP TABLE sample_table");
                log.info("Deleting sample_table");
                success = true;
            } catch (SQLException e) {
                log.error("Unable to create the database table", e);
            } finally {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException e) {
                    }
                }
            }
        }
        return success;
    }

    /**
     * Insert a person into the database
     *
     * @param person
     * @return
     */
    public boolean insertPerson(SamplePerson person) {
        boolean result = false;
        if (person != null && conn != null) {
            try (PreparedStatement statement = conn.prepareStatement("insert into sample_table(first_name, last_name, age) values ?,?,?");) {
                statement.setString(1, person.getFirstName());
                statement.setString(2, person.getLastName());
                statement.setInt(3, person.getAge());
                result = (statement.executeUpdate() == 1);
            } catch (SQLException e) {
                log.error("insert error", e);
            }
        }
        return result;
    }

    /**
     * Get all the people stored in the database
     *
     * @return
     */
    public List<SamplePerson> getAllPeople() {
        ResultSet resultSet = null;
        List<SamplePerson> list = new ArrayList<SamplePerson>();
        if (conn != null) {
            try (Statement statement = conn.createStatement()) {
                resultSet = statement.executeQuery("select * from sample_table");

                while (resultSet.next()) {
                    SamplePerson person = new SamplePerson();
                    person.setAge(resultSet.getInt("age"));
                    person.setFirstName(resultSet.getString("first_name"));
                    person.setLastName(resultSet.getString("last_name"));
                    person.setId(resultSet.getInt("id"));
                    list.add(person);
                }

            } catch (SQLException e) {
                log.error("getAllPeople error", e);
            }
        }
        return list;
    }

    /**
     * Get a person by their unique ID
     *
     * @param id
     * @return
     */
    public SamplePerson getPersonById(int id) {
        ResultSet resultSet = null;
        SamplePerson person = new SamplePerson();

        if (conn != null) {
            try (PreparedStatement statement = conn.prepareStatement("select * from sample_table where id = ?")) {
                statement.setInt(1, id);
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    person.setId(id);
                    person.setAge(resultSet.getInt("age"));
                    person.setFirstName(resultSet.getString("first_name"));
                    person.setLastName(resultSet.getString("last_name"));
                }

            } catch (SQLException e) {
                log.error("getPersonById error", e);
            }
        }
        return person;
    }

}
