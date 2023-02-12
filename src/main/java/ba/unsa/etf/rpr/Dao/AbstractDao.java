package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Domain.Idable;
import ba.unsa.etf.rpr.Exceptions.BloodException;
import java.sql.*;
import java.util.*;

/**
 * Abstract class that implements core DAO CRUD methods for every entity
 * copied from GitHub and adjusted
 * @author Azra Žunić
 */
public abstract class AbstractDao<T extends Idable> implements Dao<T>{
    /**
     * connection to database created and refactored
     */
    private static Connection connection = null;
    private String tableName;

    public AbstractDao(String tableName) {
        this.tableName = tableName;
        createConnection();
    }
    private static void createConnection(){
        if(AbstractDao.connection==null) {
            try {
                Properties p = new Properties();
                p.load(ClassLoader.getSystemResource("database.properties").openStream());
                String url = p.getProperty("db.connection_string");
                String username = p.getProperty("db.username");
                String password = p.getProperty("db.password");
                AbstractDao.connection = DriverManager.getConnection(url, username, password);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                Runtime.getRuntime().addShutdownHook(new Thread(){
                    @Override
                    public void run(){
                        try {
                            connection.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    public static Connection getConnection(){
        return AbstractDao.connection;
    }

    /**
     * Method for mapping ResultSet into Object
     * @param rs - result set from database
     * @return a Bean object for specific table
     * @throws BloodException in case of error with db
     */
    public abstract T row2object(ResultSet rs) throws Exception;

    /**
     * Method for mapping Object into Map
     * @param object - a bean object for specific table
     * @return key, value sorted map of object
     */
    public abstract Map<String, Object> object2row(T object);
    public T getById(int id) throws BloodException {
        return executeQueryUnique("SELECT * FROM "+this.tableName+" WHERE " +this.tableName.substring(0,tableName.length())+ "_id = ?", new Object[]{id});
    }
    public List<T> getAll() throws BloodException {
        return executeQuery("SELECT * FROM "+ tableName, null);
    }
    public void delete(int id) throws BloodException {
        String sql = "DELETE FROM "+tableName+" WHERE "+this.tableName.substring(0,tableName.length())+"_id = ?";
        try{
            PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            throw new BloodException(e.getMessage(), e);
        }
    }

    /**
     * add method
     * @param item bean for saving to database
     * @return
     * @throws BloodException
     */
    @Override
    public T add(T item) throws BloodException{
        Map<String, Object> row = object2row(item);
        Map.Entry<String, String> columns = prepareInsertParts(row);

        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ").append(tableName);
        builder.append(" (").append(columns.getKey()).append(") ");
        builder.append("VALUES (").append(columns.getValue()).append(")");
      //  System.out.println("ABSTRACTDAO: ");
      //  for(Map.Entry<String, String> e : columns.)
        try{
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString(), Statement.RETURN_GENERATED_KEYS);
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                if (entry.getKey().equals(this.tableName.substring(0,tableName.length())+ "_id")) continue;
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            item.setId(rs.getInt(1));

            return item;
        }catch (SQLException e){
            e.printStackTrace();
            throw new BloodException(e.getMessage(), e);
        }

    }

    /**
     * update method
     * @param item - bean to be updated. id must be populated
     * @return
     * @throws BloodException
     */
    public T update(T item) throws BloodException{
        Map<String, Object> row = object2row(item);
        String updateColumns = prepareUpdateParts(row);
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ")
                .append(tableName)
                .append(" SET ")
                .append(updateColumns)
                .append(" WHERE "+this.tableName.substring(0,tableName.length())+ "_id = ?");

        try{
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString());
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                if (entry.getKey().equals(this.tableName.substring(0,tableName.length())+ "_id"))
                    continue;
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.setObject(counter, item.getId());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            throw new BloodException(e.getMessage(), e);
        }
    }

    /**
     * Utility method for executing any kind of query
     * @param query - SQL query
     * @param params - params for query
     * @return List of objects from database
     * @throws BloodException in case of error with db
     */
    public List<T> executeQuery(String query, Object[] params) throws  BloodException {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            if (params != null){
                for(int i = 1; i <= params.length; i++){
                    stmt.setObject(i, params[i-1]);
                }
            }
            ResultSet rs = stmt.executeQuery();
            ArrayList<T> resultList = new ArrayList<>();
            while (rs.next()) {
                resultList.add(row2object(rs));
            }
            return resultList;
        } catch (Exception e) {
            throw new BloodException(e.getMessage(), e);
        }
    }

    /**
     * Utility for query execution that always return single record
     * @param query - query that returns single record
     * @param params - list of params for sql query
     * @return Object
     * @throws BloodException in case when object is not found
     */
    public T executeQueryUnique(String query, Object[] params) throws BloodException{
        List<T> result = executeQuery(query, params);
        if (result != null && result.size() == 1){
            return result.get(0);
        }else{
            throw new BloodException("Object not found");
        }
    }

    /**
     * Accepts KV storage of column names and return CSV of columns and question marks for insert statement
     * Example: (id, name, date) ?,?,?
     */
    private Map.Entry<String, String> prepareInsertParts(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();
        StringBuilder questions = new StringBuilder();

        int counter = 1;
        for (Map.Entry<String, Object> entry: row.entrySet()) {
            if (entry.getKey().equals(this.tableName.substring(0,tableName.length())+ "_id"))
                continue;
            columns.append(entry.getKey());
            questions.append("?");
            if (row.size() -1 != counter) {
                columns.append(",");
                questions.append(",");
            }
            counter++;

        }
        return new AbstractMap.SimpleEntry<String,String>(columns.toString(), questions.toString());
    }

    /**
     * Prepare columns for update statement id=?, name=?, ...
     * @param row - row to be converted intro string
     * @return String for update statement
     */
    private String prepareUpdateParts(Map<String, Object> row){
        StringBuilder col = new StringBuilder();
        int counter = 0;
        for (Map.Entry<String, Object> e : row.entrySet()) {
            if (e.getKey().equals(this.tableName+ "_id")) continue;
            counter++;
            col.append(e.getKey()).append("= ?");
            if (row.size() != counter + 1) {
                col.append(",");
            }
        }
        return col.toString();
  }
}