package com.sdkd.sftp;

import com.sdkd.utils.Constants;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by zhiran.sun on 2017/5/19.
 */
public class DatabaseExecutor {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseExecutor.class);

    private String dbName; //token+projectname

    private String sqlFilePath;

    public DatabaseExecutor(String dbName, String sqlFilePath) {
        this.dbName = dbName;
        this.sqlFilePath = sqlFilePath;
    }

    /**
     * 创建数据库
     */
    private void createDB() {

        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DriverManager.setLoginTimeout(10000);
            connection = DriverManager.getConnection(Constants.MYSQL_ROOT_URL+"mysql", Constants.MYSQL_ROOT, Constants.MYSQL_ROOT_PSWD);
            statement = connection.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbName + " DEFAULT charset utf8 COLLATE utf8_general_ci");
            logger.info("创建数据库成功-自动部署DB");
        } catch (SQLException e) {
            logger.error("创建数据库失败--自动部署DB");
        } catch (ClassNotFoundException e) {
            logger.error("加载数据库驱动失败--自动部署DB");
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error("关闭资源失败--自动部署DB");
            }

        }
    }

    /**
     * 执行SQL脚本
     */
    private void executeSQLScript() {
        Connection connection = null;
        ScriptRunner runner = null;
        Reader reader = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(Constants.MYSQL_ROOT_URL+dbName+"?useUnicode=true&characterEncoding=utf8", Constants.MYSQL_ROOT, Constants.MYSQL_ROOT_PSWD);
            runner = new ScriptRunner(connection);
            runner.setLogWriter(null);
            reader = new FileReader(sqlFilePath);
            runner.runScript(reader);
            logger.info("执行SQL脚本成功-自动部署DB");
        } catch (ClassNotFoundException e) {
            logger.error("加载数据库驱动失败--自动部署DB");
        } catch (SQLException e) {
            logger.error("执行SQL文件失败-自动部署DB");
        } catch (FileNotFoundException e) {
            logger.error("找不到SQL文件-自动部署DB");
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (runner != null) {
                    runner.closeConnection();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error("关闭资源失败--自动部署DB");
            } catch (IOException e) {
                logger.error("关闭文件流失败-自动部署DB");
            }

        }
    }


    /**
     * 部署DB
     */
    public void deployDB() {
        createDB();
        executeSQLScript();
    }

}
