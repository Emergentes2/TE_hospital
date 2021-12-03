
package com.emergentes.controlador;

import com.emergentes.dao.MedicoDAO;
import com.emergentes.dao.MedicoDAOimpl;
import com.emergentes.modelo.Medico;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "MedicoControlador", urlPatterns = {"/MedicoControlador"})
public class MedicoControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Medico me = new Medico();
            int id;
            MedicoDAO dao = new MedicoDAOimpl();
            String action = (request.getParameter("action") != null)? request.getParameter("action") : "view";
            switch (action) {
                case "add":
                    request.setAttribute("medico", me);
                    request.getRequestDispatcher("frmmedico.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    me = dao.getById(id);
                    request.setAttribute("medico", me);
                    request.getRequestDispatcher("frmmedico.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("MedicoControlador");
                    break;
                case "view":
                    // obtener la lista de registros
                    List<Medico> lista = dao.getAll();
                    request.setAttribute("medicos", lista);
                    request.getRequestDispatcher("medicos.jsp").forward(request, response);
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
        String especialidad = request.getParameter("especialidad");
        String celular = request.getParameter("celular");
        
        Medico me = new Medico();
        
        me.setId(id);
        me.setNombre(nombre);
        me.setEspecialidad(especialidad);
        me.setCelular(celular);
        
         MedicoDAO dao = new MedicoDAOimpl();
        if(id == 0){
            try {
                //nuevo registro
                dao.insert(me);
            } catch (Exception ex) {
                System.out.println("Error al insertar" +ex.getMessage());
            }
        }else{
            try {
                //Edicion de registro
                dao.update(me);
            } catch (Exception ex) {
                System.out.println("Error al editar" +ex.getMessage());
            }
        
        }
        response.sendRedirect("MedicoControlador");
    
    }
    


}
