
package com.emergentes.dao;

import com.emergentes.modelo.Paciente;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class PacienteDAOimpl extends ConexionDB implements PacienteDAO  {

    @Override
    public void insert(Paciente paciente) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("INSERT INTO pacientes (nombre,edad,direccion,celular) values (?,?,?,?)");
            ps.setString(1, paciente.getNombre());
            ps.setInt(2, paciente.getEdad());
            ps.setString(3, paciente.getDireccion());
            ps.setString(4, paciente.getCelular());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Paciente paciente) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE pacientes SET nombre = ?,edad = ?,direccion = ?,celular = ? where id = ?");
            ps.setString(1, paciente.getNombre());
            ps.setInt(2, paciente.getEdad());
            ps.setString(3, paciente.getDireccion());
            ps.setString(4, paciente.getCelular());
            ps.setInt(5, paciente.getId());
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
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM pacientes WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Paciente getById(int id) throws Exception {
        Paciente pa = new Paciente();
        try {
            this.conectar();
            
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM pacientes WHERE id = ?");
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                pa.setId(rs.getInt("id"));
                pa.setNombre(rs.getString("nombre"));
                pa.setEdad(rs.getInt("edad"));
                pa.setDireccion(rs.getString("direccion"));
                pa.setCelular(rs.getString("celular"));
            }
            
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return pa;
    }

    @Override
    public List<Paciente> getAll() throws Exception {
        List<Paciente> lista = null;
        try {
            this.conectar();
            
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM pacientes");
            ResultSet rs = ps.executeQuery();
            
            lista = new ArrayList<Paciente>();
            
            while(rs.next()){
                Paciente pa = new Paciente();
                
                pa.setId(rs.getInt("id"));
                pa.setNombre(rs.getString("nombre"));
                pa.setEdad(rs.getInt("edad"));
                pa.setDireccion(rs.getString("direccion"));
                pa.setCelular(rs.getString("celular"));
                
                lista.add(pa);
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
