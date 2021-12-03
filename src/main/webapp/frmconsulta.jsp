<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <title>Consultas medicas</title>
    </head>
    <body>
        <div class="container">
            <h1>Formulario de consultas</h1>
            <jsp:include page="WEB-INF/menu.jsp">
                <jsp:param name="opcion" value="consultas"/>
            </jsp:include>
            <br>
            <form action="ConsultaControlador" method="post">
                <input type="hidden" name="id" value="${consulta.id}">
                <div class="form-group">
                    <label for="" class="form-label">Paciente</label>
                    <select name="paciente_id" class="form-control">
                        <option value="">---seleccione---</option>
                        <c:forEach var="item" items="${lista_pacientes}">
                            <option value="${item.id}" 
                                    <c:if test="${consulta.paciente_id ==item.id}">
                                        selected
                                    </c:if>
                                        >${item.nombre}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="" class="form-label">Medico</label>
                    <select name="medico_id" class="form-control">
                        <option value="">---seleccione---</option>
                        <c:forEach var="item" items="${lista_medicos}">
                            <option value="${item.id}" 
                                    <c:if test="${consulta.medico_id ==item.id}">
                                        selected
                                    </c:if>
                                        >${item.nombre}</option>
                        </c:forEach>   
                    </select>
                </div>
                <div class="form-group">
                    <label for="" class="form-label">Diagnostico</label>
                    <input type="text" class="form-control" name="diagnostico" value="${consulta.diagnostico}" placeholder="escriba el diagnostico">
                </div>
                <div class="form-group">
                    <label for="" class="form-label">Tratamiento</label>
                    <input type="text" class="form-control" name="tratamiento" value="${consulta.tratamiento}" placeholder="escriba el tratamiento">
                </div>
                <div class="form-group">
                    <label for="" class="form-label">Fecha</label>
                    <input type="text" class="form-control" name="fecha" value="${venta.fecha}" placeholder="escriba la fecha">
                </div>

                <button type="submit" class="btn btn-primary">Enviar</button>
            </form> 


        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    </body>
</html>
