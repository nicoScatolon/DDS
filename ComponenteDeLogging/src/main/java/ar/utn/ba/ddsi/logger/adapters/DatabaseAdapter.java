package ar.utn.ba.ddsi.logger.db;

import java.util.Map;

public interface DatabaseAdapter {
    void connect();

    void insert(String tableName, Map<String, Object> stringObjectMap);

    void disconnect();
}
