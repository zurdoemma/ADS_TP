/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.frc.utn.avads.db.service;

import java.util.List;
import java.util.Map;

/**
 *
 * @author ecarballo
 */
public interface DBService {
    
    void startDB(String dbServer, String dbPassword, String dbUrl, String DBPort, String DBName);
    void closeDB();
    void insertCollection(String nomCollection, String collection);
    List<String> getAllCollection(String nomCollection);
    Long getMaxIdProceso(String nomCollection, String atribute);
    List<String> findObjectCollection(String nomCollection, String atribute, Object value);
    List<String> findObjectCollectionMultipleAtributes(String nomCollection, Map<String,Object> atributeValue);
    boolean removeObjectCollection(String nomCollection, String atribute, Object value);
    Long getCountObjectsCollection(String nomCollection);
    boolean updateObjectCollection(String nomCollection, Map<String, Object> atributeValueSearch, Map<String, Object> atributeValueUpdate);
    
    
}
