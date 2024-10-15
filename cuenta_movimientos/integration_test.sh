#!/bin/bash

# Crear un cliente en el microservicio cliente_persona
CLIENTE_CREACION_RESPONSE=$(curl -s -o /dev/null -w "%{http_code}" -X POST http://localhost:8081/clientes \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Danny Diaz",
    "genero": "Masculino",
    "edad": 30,
    "direccion": "123 Calle Falsa",
    "telefono": "555-1234",
    "contrasena": "5551234",
    "estado": true
  }')

if [ "$CLIENTE_CREACION_RESPONSE" -eq 200 ] || [ "$CLIENTE_CREACION_RESPONSE" -eq 201 ]; then
  echo "Cliente creado con éxito."
else
  echo "Error al crear cliente. Código de respuesta: $CLIENTE_CREACION_RESPONSE"
  exit 1
fi

# Crear la primera cuenta para el cliente
CUENTA1_CREACION_RESPONSE=$(curl -s -o /dev/null -w "%{http_code}" -X POST http://localhost:8082/cuentas \
  -H "Content-Type: application/json" \
  -d '{
    "tipoCuenta": "Ahorro",
    "saldoInicial": 1000.00,
    "estado": true,
    "clienteId": 1,
    "nombreCliente": "Danny12 Lalito"
  }')

if [ "$CUENTA1_CREACION_RESPONSE" -eq 200 ] || [ "$CUENTA1_CREACION_RESPONSE" -eq 201 ]; then
  echo "Primera cuenta creada con éxito."
else
  echo "Error al crear la primera cuenta. Código de respuesta: $CUENTA1_CREACION_RESPONSE"
  exit 1
fi

# Crear la segunda cuenta para el cliente
CUENTA2_CREACION_RESPONSE=$(curl -s -o /dev/null -w "%{http_code}" -X POST http://localhost:8082/cuentas \
  -H "Content-Type: application/json" \
  -d '{
    "tipoCuenta": "Corriente",
    "saldoInicial": 2000.00,
    "estado": true,
    "clienteId": 1,
    "nombreCliente": "Danny12 Lalito"
  }')

if [ "$CUENTA2_CREACION_RESPONSE" -eq 200 ] || [ "$CUENTA2_CREACION_RESPONSE" -eq 201 ]; then
  echo "Segunda cuenta creada con éxito."
else
  echo "Error al crear la segunda cuenta. Código de respuesta: $CUENTA2_CREACION_RESPONSE"
  exit 1
fi

# Crear 10 movimientos en la primera cuenta
for i in {1..10}
do
  MOVIMIENTO_CREACION_RESPONSE=$(curl -s -o /dev/null -w "%{http_code}" -X POST http://localhost:8082/movimientos \
    -H "Content-Type: application/json" \
    -d '{
      "cuenta": {
        "id": 1
      },
      "valor": -10.0,
      "estado": true
    }')

  if [ "$MOVIMIENTO_CREACION_RESPONSE" -eq 200 ] || [ "$MOVIMIENTO_CREACION_RESPONSE" -eq 201 ]; then
    echo "Movimiento $i en la primera cuenta creado con éxito."
  else
    echo "Error al crear movimiento $i en la primera cuenta. Código de respuesta: $MOVIMIENTO_CREACION_RESPONSE"
    exit 1
  fi
done

# Crear 10 movimientos en la segunda cuenta
for i in {1..10}
do
  MOVIMIENTO_CREACION_RESPONSE=$(curl -s -o /dev/null -w "%{http_code}" -X POST http://localhost:8082/movimientos \
    -H "Content-Type: application/json" \
    -d '{
      "cuenta": {
        "id": 2
      },
      "valor": 1700.0,
      "estado": true
    }')

  if [ "$MOVIMIENTO_CREACION_RESPONSE" -eq 200 ] || [ "$MOVIMIENTO_CREACION_RESPONSE" -eq 201 ]; then
    echo "Movimiento $i en la segunda cuenta creado con éxito."
  else
    echo "Error al crear movimiento $i en la segunda cuenta. Código de respuesta: $MOVIMIENTO_CREACION_RESPONSE"
    exit 1
  fi
done

echo "Prueba de integración completada con éxito."