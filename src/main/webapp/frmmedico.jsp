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
            <h1>Formulario de medicos</h1>
            <jsp:include page="WEB-INF/menu.jsp">
                <jsp:param name="opcion" value="medicos"/>
            </jsp:include>
            <br>
            <form action="MedicoControlador" method="post">
                <input type="hidden" name="id" value="${medico.id}">
                <div class="form-group">
                    <label for="" class="form-label">Nombre</label>
                    <input type="text" class="form-control" name="nombre" value="${medico.nombre}" placeholder="escriba su nombre">
                 </div>
                <div class="form-group">
                    <label for="" class="form-label">Especialidad</label>
                    <input type="text" class="form-control" name="descripcion" value="${medico.especialidad}" placeholder="describa su especialidad">
                </div>
                <div class="form-group">
                    <label for="" class="form-label">Celular</label>
                    <input type="text" class="form-control" name="celular" value="${medico.celular}" placeholder="escriba el celular">
                </div>

                <button type="submit" class="btn btn-primary">Enviar</button>
            </form> 


        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    </body>
</html>
