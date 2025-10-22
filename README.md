# Sistema de Arrendamiento (Spring Boot)

Guía de uso y desarrollo del proyecto para publicar en GitHub.

## Descripción
Aplicación REST para gestionar un sistema de arrendamiento: clientes, propiedades, disponibilidades, reservaciones, reseñas y pagos

## Tecnologías
- Java (Spring Boot)
- Spring Web, Spring Data JPA
- H2 (desarrollo) / MySQL (configurable)

## Requisitos
- JDK 17 (recomendado para Spring Boot 3.x)
- Maven (wrapper incluido: `mvnw`)

## Ejecutar en desarrollo
1. Abrir terminal en `sistema-arrendamiento`.
2. Ejecutar:
   - Windows: `.
   mvnw spring-boot:run`
   - Linux/Mac: `./mvnw spring-boot:run`
3. API disponible en `http://localhost:8070/`.


## Build y ejecución del JAR
- Compilar: `./mvnw clean package` (Windows: `.
  mvnw clean package`)
- Ejecutar: `java -jar target/sistema-arrendamiento-*.jar`

## Configuración (application.properties)
- Puerto: `server.port=8070`
- H2 (dev): `spring.datasource.url=jdbc:h2:mem:sistema_arrendamiento`
- Formato de fecha JSON: `spring.jackson.date-format=yyyy-MM-dd`

Para usar MySQL, sustituye la configuración H2 por la sección comentada y ajusta credenciales.

## Endpoints principales
Base URL: `http://localhost:8070`

- Clientes (`/api/clientes`)
  - `GET /api/clientes`
  - `GET /api/clientes/{id}`
  - `GET /api/clientes/tipo/{tipo}`
  - `POST /api/clientes`
  - `PUT /api/clientes/{id}`
  - `DELETE /api/clientes/{id}`

- Propiedades (`/api/propiedades`)
  - `GET /api/propiedades`
  - `GET /api/propiedades/{id}`
  - `GET /api/propiedades/tipo/{tipo}`
  - `POST /api/propiedades`
  - `PUT /api/propiedades/{id}`
  - `DELETE /api/propiedades/{id}`

- Disponibilidad (`/api/disponibilidad`)
  - `GET /api/disponibilidad`
  - `GET /api/disponibilidad/{id}`
  - `POST /api/disponibilidad`
  - `PUT /api/disponibilidad/{id}`
  - `DELETE /api/disponibilidad/{id}`

- Reservaciones (`/api/reservaciones`)
  - `GET /api/reservaciones`
  - `GET /api/reservaciones/{id}`
  - `POST /api/reservaciones`
  - `PUT /api/reservaciones/{id}`
  - `DELETE /api/reservaciones/{id}`

- Reseñas (`/api/reseñas`)
  - `GET /api/reseñas`
  - `GET /api/reseñas/{id}`
  - `POST /api/reseñas`
  - `PUT /api/reseñas/{id}`
  - `DELETE /api/reseñas/{id}`

- Pagos (`/api/pagos`)
  - `GET /api/pagos`
  - `GET /api/pagos/{id}`
  - `POST /api/pagos`
  - `PUT /api/pagos/{id}`
  - `DELETE /api/pagos/{id}`

## Ejemplos de uso (curl)
- Listar clientes:
  ```bash
  curl http://localhost:8070/api/clientes
  ```
- Crear cliente:
  ```bash
  curl -X POST http://localhost:8070/api/clientes \
    -H "Content-Type: application/json" \
    -d '{
      "tipo":"INDIVIDUAL",
      "tipoClienteMensaje":"REMITENTE",
      "nombres":"Juan",
      "ine":"ABC123456",
      "apellidoPat":"Pérez",
      "apellidoMat":"López",
      "email":"juan@example.com",
      "telefono":"5551234567",
      "direccion":"Calle 1 123",
      "fechaNacimiento":"1990-05-10",
      "fechaRegistro":"2024-10-21T18:30:00",
      "status":"ACTIVO",
      "password":"secreta",
      "ultimoAcceso":"2024-10-21T18:30:00"
    }'
  ```
- Crear propiedad:
  ```bash
  curl -X POST http://localhost:8070/api/propiedades \
    -H "Content-Type: application/json" \
    -d '{
      "tipo":"APARTAMENTO",
      "titulo":"Depto céntrico",
      "descripcion":"Bonito depto",
      "direccion":"Av. Principal 123",
      "ciudad":"CDMX",
      "codigoPostal":"01000",
      "pais":"Mexico",
      "latitud":19.43,
      "longitud":-99.13,
      "precioNoche":95.0,
      "capacidadPersonas":3,
      "numeroHabitaciones":2,
      "numeroBanos":1,
      "metrosCuadrados":70,
      "comodidades":"WiFi,A/C",
      "normasCasa":"No fumar",
      "estado":"DISPONIBLE",
      "fechaCreacion":"2024-10-21",
      "fechaActualizacion":"2024-10-21"
    }'
  ```
- Crear disponibilidad (propiedad id=1):
  ```bash
  curl -X POST http://localhost:8070/api/disponibilidad \
    -H "Content-Type: application/json" \
    -d '{
      "propiedad":{"id":1},
      "fecha":"2024-12-01T00:00:00",
      "disponible":true,
      "precioEspecial":120.0
    }'
  ```

## Enums (valores permitidos)
- `TipoCliente`: `REGULAR`, `PREMIUM`, `VIP`, `INDIVIDUAL`, `EMPRESA`, `AGENCIA`
- `TipoClienteMensaje`: `REMITENTE`, `DESTINATARIO`
- `Status`: `ACTIVO`, `INACTIVO`, `SUSPENDIDO`
- `TipoPropiedad`: `APARTAMENTO`, `CASA`, `CONDOMINIO`, `VILLA`, `ESTUDIO`, `LOFT`, `DUPLEX`, `TRIPLEX`, `PENTHOUSE`, `BUNGALOW`, `CABAÑA`, `CHALET`, `RANCHO`, `GRANJA`, `OTRO`
- `EstadoPropiedad`: `DISPONIBLE`, `NO_DISPONIBLE`, `EN_MANTENIMIENTO`, `RESERVADA`, `VENDIDA`
- `EstadoReservacion`: `PENDIENTE`, `CONFIRMADA`, `CANCELADA`, `COMPLETADA`
- `MetodoPago`: `TARJETA_CREDITO`, `TARJETA_DEBITO`, `PAYPAL`, `TRANSFERENCIA_BANCARIA`
- `EstadoPago`: `PENDIENTE`, `COMPLETADO`, `FALLIDO`, `REEMBOLSADO`

## Notas de datos
- Fechas `Date`: `yyyy-MM-dd` (ej. `2024-11-01`).
- Fechas `LocalDateTime`: `yyyy-MM-dd'T'HH:mm:ss` (ej. `2024-11-01T12:30:00`).
- Para referenciar entidades existentes (propiedad/cliente/reservación) usa `{ "id": <ID> }`.



---