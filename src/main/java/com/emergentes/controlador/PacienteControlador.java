
package com.emergentes.controlador;

import com.emergentes.dao.PacienteDAO;
import com.emergentes.dao.PacienteDAOimpl;
import com.emergentes.modelo.Paciente;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PacienteControlador", urlPatterns = {"/PacienteControlador"})
public class PacienteControlador extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Paciente pa = new Paciente();
            int id;
            PacienteDAO dao = new PacienteDAOimpl();
            String action = (request.getParameter("action") != null)? request.getParameter("action") : "view";
            switch (action) {
                case "add":
                    request.setAttribute("paciente", pa);
                    request.getRequestDispatcher("frmpaciente.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    pa = dao.getById(id);
                    request.setAttribute("paciente", pa);
                    request.getRequestDispatcher("frmpaciente.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("PacienteControlador");
                    break;
                case "view":
                    // obtener la lista de registros
                    List<Paciente> lista = dao.getAll();
                    request.setAttribute("pacientes", lista);
                    request.getRequestDispatcher("pacientes.jsp").forward(request, response);
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Error" +ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        int edad = Integer.parseInt(request.getParameter("edad"));
        String direccion = request.getParameter("direccion");
        String celular = request.getParameter("celular");
        
        Paciente pa = new Paciente();
        
        pa.setId(id);
        pa.setNombre(nombre);
        pa.setEdad(edad);
        pa.setDireccion(direccion);
        pa.setCelular(celular);
        
         PacienteDAO dao = new PacienteDAOimpl();
        if(id == 0){
            try {
                //nuevo registro
                dao.insert(pa);
            } catch (Exception ex) {
                System.out.println("Error al insertar" +ex.getMessage());
            }
        }else{
            try {
                //Edicion de registro
                dao.update(pa);
            } catch (Exception ex) {
                System.out.println("Error al editar" +ex.getMessage());
            }
        
        }
        response.sendRedirect("PacienteControlador");
    }
    

}
