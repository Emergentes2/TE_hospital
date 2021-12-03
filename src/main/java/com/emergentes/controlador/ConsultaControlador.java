
package com.emergentes.controlador;

import com.emergentes.dao.ConsultaDAO;
import com.emergentes.dao.ConsultaDAOimpl;
import com.emergentes.dao.MedicoDAO;
import com.emergentes.dao.MedicoDAOimpl;
import com.emergentes.dao.PacienteDAO;
import com.emergentes.dao.PacienteDAOimpl;
import com.emergentes.modelo.Consulta;
import com.emergentes.modelo.Medico;
import com.emergentes.modelo.Paciente;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ConsultaControlador", urlPatterns = {"/ConsultaControlador"})
public class ConsultaControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ConsultaDAO dao = new ConsultaDAOimpl();
            MedicoDAO daoMedico = new MedicoDAOimpl();
            PacienteDAO daoPaciente = new PacienteDAOimpl();
            int id;
            List<Medico> lista_medicos = null;
            List<Paciente> lista_pacientes = null;
            Consulta consulta = new Consulta();

            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            switch (action) {
                case "add":
                    lista_medicos = daoMedico.getAll();
                    lista_pacientes = daoPaciente.getAll();
                    request.setAttribute("lista_medicos", lista_medicos);
                    request.setAttribute("lista_pacientes", lista_pacientes);        
                    request.setAttribute("consulta", consulta);
                    request.getRequestDispatcher("frmconsulta.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    consulta = dao.getById(id);
                    lista_medicos = daoMedico.getAll();
                    lista_pacientes = daoPaciente.getAll();
                    request.setAttribute("lista_medicos", lista_medicos);
                    request.setAttribute("lista_pacientes", lista_pacientes);
                    request.setAttribute("consulta", consulta);
                    request.getRequestDispatcher("frmconsulta.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("VentaControlador");
                    break;
                case "view":
                    List<Consulta> lista = dao.getAll();
                    request.setAttribute("consultas", lista);
                    request.getRequestDispatcher("consultas.jsp").forward(request, response);
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Error" + ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int paciente_id = Integer.parseInt(request.getParameter("paciente_id"));
        int medico_id = Integer.parseInt(request.getParameter("medico_id"));
        String diagnostico = request.getParameter("diagnostico");
        String tratamiento = request.getParameter("tratamiento");
        String fecha = request.getParameter("fecha");

        Consulta consulta = new Consulta();

        consulta.setId(id);
        consulta.setMedico_id(medico_id);
        consulta.setPaciente_id(paciente_id);
        consulta.setDiagnostico(diagnostico);
        consulta.setTratamiento(tratamiento);
        consulta.setFecha(convierteFecha(fecha));
        
        if (id == 0) {
            // nuevo
            ConsultaDAO dao = new ConsultaDAOimpl();
            try {
                dao.insert(consulta);
                response.sendRedirect("ConsultaControlador");
            } catch (Exception ex) {
                Logger.getLogger(ConsultaControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            //editar
            ConsultaDAO dao = new ConsultaDAOimpl();
            try {
                dao.update(consulta);
                response.sendRedirect("ConsultaControlador");
            } catch (Exception ex) {
                Logger.getLogger(ConsultaControlador.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
    }
    public Date convierteFecha(String fecha) {
        Date fechaBD = null;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        java.util.Date fechaTMP;

        try {
            fechaTMP = formato.parse(fecha);
            fechaBD = new Date(fechaTMP.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(ConsultaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }

        return fechaBD;
    }

}
