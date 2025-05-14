package ar.utn.ba.ddsi.logger.db;

import ar.utn.ba.ddsi.logger.db.connectors.MySQLConnector;
import ar.utn.ba.ddsi.logger.utils.ConfigReader;

import java.util.Map;

public class MySQLDatabaseAdapter implements DatabaseAdapter {
    private MySQLConnector mySQLConnector;
    private ConfigReader config;

    public MySQLDatabaseAdapter() {
        mySQLConnector = new MySQLConnector();
        this.config = new ConfigReader();
    }

    @Override
    public void connect() {
        try {
            this.mySQLConnector.connect(
                    this.config.getProperty("mySQLURLConnection"),
                    this.config.getProperty("mySQLUsername"),
                    this.config.getProperty("mySQLPassword")
            );
        }catch (Exception e){
            //ToDo: convertir excepcion a no chequeada(RunTimeException)
        }

    }

    @Override
    public void insert(String tableName, Map<String, Object> stringObjectMap) {
        try {
            String[] columnNames = stringObjectMap.keySet().toArray(new String[0]);
            Object[] values = stringObjectMap.values().toArray();
            this.mySQLConnector.insert(tableName,columnNames,values);
        }catch (Exception e){
            //ToDo: convertir excepcion a no chequeada(RunTimeException)
        }

    }

    @Override
    public void disconnect() {
        try {
            this.mySQLConnector.disconnect();
        }catch (Exception e){
            //ToDo: convertir excepcion a no chequeada(RunTimeException)
        }

    }
}
