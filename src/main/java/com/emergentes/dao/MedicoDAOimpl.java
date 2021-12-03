
package com.emergentes.dao;

import com.emergentes.modelo.Medico;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class MedicoDAOimpl extends ConexionDB implements MedicoDAO{

    @Override
    public void insert(Medico medico) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO medicos (nombre,especialidad,celular) values (?,?,?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, medico.getNombre());
            ps.setString(2, medico.getEspecialidad());
            ps.setString(3, medico.getCelular());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Medico medico) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE medicos SET nombre=?,especialidad=?,celular=? WHERE id=?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, medico.getNombre());
            ps.setString(2, medico.getEspecialidad());
            ps.setString(3, medico.getCelular());
            ps.setInt(4, medico.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            this.conectar();
            String sql = "DELETE FROM medicos WHERE id=?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Medico getById(int id) throws Exception {
        Medico me = new Medico();
        try {
            this.conectar();
            String sql = "SELECT * FROM medicos WHERE id = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                me.setId(rs.getInt("id"));
                me.setNombre(rs.getString("nombre"));
                me.setEspecialidad(rs.getString("especialidad"));
                me.setCelular(rs.getString("celular"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return me;
    }

    @Override
    public List<Medico> getAll() throws Exception {
        List<Medico> lista = null;
        try {
            this.conectar();
            String sql = "SELECT * FROM medicos";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            lista = new ArrayList<Medico>();
            while(rs.next()){
                Medico m = new Medico();
                m.setId(rs.getInt("id"));
                m.setNombre(rs.getString("nombre"));
                m.setEspecialidad(rs.getString("especialidad"));
                m.setCelular(rs.getString("celular"));
                lista.add(m);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }
    
    
}
