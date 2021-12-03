
package com.emergentes.dao;

import com.emergentes.modelo.Medico;
import java.util.List;


public interface MedicoDAO {
    
     public void insert (Medico medico) throws Exception;
    public void update(Medico medico) throws Exception;
    public void delete(int id) throws Exception;
    public Medico getById(int id) throws Exception;
    public List<Medico> getAll() throws Exception;
    
}
