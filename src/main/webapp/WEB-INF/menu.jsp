<%
    String opcion = request.getParameter("opcion");
%>

<ul class="nav nav-tabs">
    <li class="nav-item">
        <a class="nav-link <%=(opcion.equals("medicos")? "active":"") %>" href="MedicoControlador">Medicos</a>
    </li>
    <li class="nav-item">
        <a class="nav-link <%=(opcion.equals("pacientes")? "active":"") %>" href="PacienteControlador">Pacientes</a>
    </li>
    <li class="nav-item">
        <a class="nav-link <%=(opcion.equals("consultas")? "active":"") %>" href="ConsultaControlador">Consultas</a>
    </li>
</ul>
