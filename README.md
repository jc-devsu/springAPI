PRUEBA DE JAVA, ELABORAR API CON SPRING

Implementación basica de API para un banco pequeño.

Cada microservicio cuenta con su base de datos, y se comunican con llamadas asincronas.



PASOS PARA CORRER EL PROYECTO:

1) Instale docker desktop de aqui: `https://docs.docker.com/desktop/install/mac-install/`, luego corra docker desktop.



2) Cree la red de docker con el comando:

`docker network create cliente_persona_network`   




3) Corra el microservicio "cliente_persona", para esto primero debe correr el contenedor de su base de datos con el comando: 

`docker compose up postgres-db-cliente -d`

si realizo cambios en el codigo, construya el proyecto con el comando:

`./gradlew build`

luego levante el contenedor del microservicio cliente_persona con el comando:

`docker compose up`

NOTA: Este contenedor correra las pruebas unitarios contenidas en el directorio Test, donde se encuentra la prueba unitaria para el dominio Cliente.  Esto se hace corriendo las pruebas desde el Dockerfile del microservicio.

Puede eliminar el contenedor con el comando:

`docker compose down -v`  




4) Corra el microservicio "cuenta_movimiento", para esto primero debe correr el contenedor de su base de datos con el comando: 

`docker compose up postgres-db-movimientos -d`

si realizo cambios en el codigo, construya el proyecto con el comando:

`./gradlew build`

luego levante el contenedor del microservicio cliente_persona con el comando:

`docker compose up`

NOTA: Este contenedor correra la prueba de integracion contenida en el archivo `integration_test.sh`





Importante:

- Primero debe crear un cliente y para poder crear una cuenta debe pasar un id valid de cliente, pues el sistema no permite crear cuentas si no estan vinculadas a un cliente valido.
- No se pueden hacer movimientos si el saldo final es negativo, si no se mostrara el error respectivo.
- Reporte de movimientos por cliente, por fechas.
- El archivo BancoAPI.postman_collection.json contiene la exportacion de las solicitudes postman para validar endpoints, ya se validan con la prueba de integración.
- El archivo BaseDatos.sql contiene el script para generar las dos bases de datos, aunque no es necesario porque se generan en contenedores docker, si desea guardar los datos en guardados entre reincios de los contenedores de los microservicios,
  cambie la propiedad `spring.jpa.hibernate.ddl-auto=create` a `update` en los archivos `application.propierties` de cada microservicio.
  

