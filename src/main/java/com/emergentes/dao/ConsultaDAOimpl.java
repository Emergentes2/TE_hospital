
package com.emergentes.dao;

import com.emergentes.modelo.Consulta;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAOimpl extends ConexionDB implements ConsultaDAO {

    @Override
    public void insert(Consulta consulta) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO consultas (paciente_id,medico_id,diagnostico,tratamiento,fecha) values (?,?,?,?,?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);

            ps.setInt(1, consulta.getPaciente_id());
            ps.setInt(2, consulta.getMedico_id());
            ps.setString(3, consulta.getDiagnostico());
            ps.setString(4, consulta.getTratamiento());
            ps.setDate(5, consulta.getFecha());
            ps.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Consulta consulta) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE consultas SET paciente_id=?,medico_id=?,diagnostico=?,tratamiento=?,fecha=? WHERE id=?";
            PreparedStatement ps = this.conn.prepareStatement(sql);

            ps.setInt(1, consulta.getPaciente_id());
            ps.setInt(2, consulta.getMedico_id());
            ps.setString(3, consulta.getDiagnostico());
            ps.setString(4, consulta.getTratamiento());
            ps.setDate(5, consulta.getFecha());
            ps.setInt(6, consulta.getId());
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
            String sql = "DELETE FROM consultas WHERE id = ?";
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
    public Consulta getById(int id) throws Exception {
         Consulta c = new Consulta();
        try {
            this.conectar();
            String sql = "SELECT * FROM consultas WHERE id = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                c.setId(rs.getInt("id"));
                c.setPaciente_id(rs.getInt("paciente_id"));
                c.setMedico_id(rs.getInt("medico_id"));
                c.setDiagnostico(rs.getString("diagnostico"));
                c.setTratamiento(rs.getString("tratamiento"));
                c.setFecha(rs.getDate("fecha"));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return c;
    }

    @Override
    public List<Consulta> getAll() throws Exception {
        List<Consulta> lista = null;
        try {
            this.conectar();
            String sql = "SELECT c.*,m.nombre as medico,p.nombre as paciente FROM consultas c";
            sql +="LEFT JOIN medicos m ON c.medico_id = m.id";
            sql +="LEFT JOIN pacientes p ON c.paciente_id = p.id";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<Consulta>();
            while(rs.next()){
                Consulta c = new Consulta();
                c.setId(rs.getInt("id"));
                c.setPaciente_id(rs.getInt("medico_id"));
                c.setMedico_id(rs.getInt("paciente_id"));
                c.setDiagnostico(rs.getString("diagnostico"));
                c.setTratamiento(rs.getString("tratamiento"));
                c.setFecha(rs.getDate("fecha"));
                c.setPaciente(rs.getString("paciente"));
                c.setMedico(rs.getString("medico"));
                lista.add(c);
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
