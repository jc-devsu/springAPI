####

Para correr el proyecto, primero al directorio "cliente_persona" y corra el contenedor de su base de datos con el comando: 

`docker compose up postgres-db-cliente -d`

si realizo cambios en el codigo, construya el proyecto con el comando:

`./gradlew build`

luego levante el contenedor del microservicio cliente_persona con el comando:

`docker compose up`

Este contenedor correra las pruebas unitarios contenidas en el directorio Test, donde se encuentra la prueba unitaria para el dominio Cliente.  Esto se hace corriendo las pruebas desde el Dockerfile del microservicio.

#####


Seguidamente muevase al directorio "cuenta_movimiento" y corra el contenedor de su base de datos con el comando: 

`docker compose up postgres-db-movimientos -d`

si realizo cambios en el codigo, construya el proyecto con el comando:

`./gradlew build`

luego levante el contenedor del microservicio cliente_persona con el comando:

`docker compose up`

Este contenedor correra la prueba de integracion contenida en el archivo `integration_test.sh`

####

Importante:

- Primero debe crear un cliente y para poder crear una cuenta debe pasar un id valid de cliente, pues el sistema no permite crear cuentas si no estan vinculadas a un cliente valido.
- No se pueden hacer movimientos si el saldo final es negativo, si no se mostrara el error respectivo.
- Reporte de movimientos por cliente, por fechas.
- El archivo BancoAPI.postman_collection.json contiene la exportacion de las solicitudes postman para validar endpoints, ya se validan con la prueba de integración.
- El archivo BaseDatos.sql contiene el script para generar las dos bases de datos, aunque no es necesario porque se generan en contenedores docker, si desea guardar los datos en guardados entre reincios de los contenedores de los microservicios,
  cambie la propiedad `spring.jpa.hibernate.ddl-auto=create` a `update` en los archivos `application.propierties` de cada microservicio.
  

