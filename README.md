# 📦 Proyecto Bazar - Sistema de Gestión

Este proyecto es un sistema de gestión de clientes, productos y ventas para un bazar. Utiliza una estructura **monolítica** bajo el patrón **MVC (Modelo-Vista-Controlador)**, desarrollado con **Java 8**, **Spring Boot 2.7.15**, y conexión a **MariaDB** usando **XAMPP**. El frontend está implementado con **Thymeleaf** y **Bootstrap** junto con sus íconos, brindando una interfaz web moderna y responsiva.

![image](https://github.com/user-attachments/assets/1f5e2e9a-ae36-47cd-9178-4339851c6b98)

## 🛠 Tecnologías Utilizadas

- **Java 8**: Lenguaje de programación principal del backend.
- **Spring Boot 2.7.15**: Framework para la creación de aplicaciones empresariales con enfoque en simplicidad y rapidez.
- **MariaDB**: Base de datos relacional utilizada para almacenar información de clientes, productos y ventas.
- **XAMPP**: Utilizado para la configuración y administración de MariaDB en el entorno local.
- **Thymeleaf**: Motor de plantillas usado para renderizar las vistas HTML dinámicamente desde el backend.
- **Bootstrap 5**: Framework de CSS para el diseño de una interfaz web moderna y responsiva.
- **Bootstrap Icons**: Colección de íconos integrados para mejorar la experiencia visual.

## 🌐 Arquitectura del Proyecto

El proyecto sigue el patrón **MVC (Modelo-Vista-Controlador)**, dividiendo la lógica en las siguientes capas:

1. **Controller**: Controladores de Thymeleaf, responsables de manejar las solicitudes HTTP y renderizar las vistas HTML.
2. **RestController**: Controladores dedicados a la API Rest, que permiten realizar operaciones CRUD sobre los recursos del sistema.

### 📁 Estructura de Paquetes

```bash
src/
├── main/
│   ├── java/
│   │   ├── com/bazar/controller/          # Controladores para las vistas Thymeleaf
│   │   ├── com/bazar/restcontroller/      # Controladores para la API Rest
│   │   ├── com/bazar/model/               # Clases del modelo (Cliente, Producto, Venta)
│   │   ├── com/bazar/dto/                 # Objetos de transferencia de datos (DTOs)
│   │   ├── com/bazar/repository/          # Repositorios para acceso a datos (CRUD, consultas)
│   │   ├── com/bazar/service/             # Servicios para manejar la lógica de negocio
│   └── resources/
│       ├── templates/                     # Vistas HTML Thymeleaf
│       ├── static/                        # Archivos estáticos (CSS, JS, imágenes)
│       └── application.properties         # Configuraciones de la aplicación (Base de datos, puerto, etc.)
```

---

## 🌟 Características del Proyecto

### ✅ Funcionalidades Principales

- **Gestión de Clientes**: Alta, baja, modificación y consulta de clientes.
- **Gestión de Productos**: Administración de productos disponibles para la venta.
- **Gestión de Ventas**: Creación y seguimiento de ventas realizadas.

---

## 🖥 Cómo Iniciar el Proyecto

1. **Clonar el Repositorio**:
   ```bash
   git clone https://github.com/usuario/proyecto-bazar.git
   ```

2. **Configurar la Base de Datos**:
   - Iniciar XAMPP y asegurarse de que MariaDB esté corriendo.
   - Crear una base de datos llamada `bazar_db` en MariaDB.

3. **Modificar el archivo `application.properties`**:
   Configura las credenciales de la base de datos:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/bazar_db?useSSL=false&serverTimezone=UTC
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   ```

4. **Ejecutar la Aplicación**:
   ```bash
   ./mvnw spring-boot:run
   ```

5. **Acceder a la Aplicación**:
   - **Interfaz Web (Thymeleaf)**: [http://localhost:8080](http://localhost:8080)
   - **API Rest**: [http://localhost:8080/metodoAUtilizar](http://localhost:8080/metodoAUtilizar)

---

## 🌐 API Rest - Métodos y Uso

### 📋 Endpoints Disponibles

| Método HTTP | Endpoint                            | Descripción                             |
|-------------|-------------------------------------|-----------------------------------------|
| **GET**     | `/cliente/verClientes`              | Obtener la lista de todos los clientes. |
| **GET**     | `/cliente/verCliente/{id}`        | Obtener un cliente por su ID.          |
| **GET**     | `/producto/verProductos`              | Obtener la lista de todos los productos. |
| **GET**     | `/producto/verProducto/{id}`        | Obtener un producto por su ID.          |
| **GET**     | `/venta/verVentass`              | Obtener la lista de todas las ventas. |
| **GET**     | `/venta/verVenta/{id}`        | Obtener una venta por su ID.          |
| **POST**    | `/cliente/crearCliente`             | Crear un nuevo cliente.                 |
| **POST**    | `/producto/crearProducto`           | Crear un nuevo producto.                |
| **POST**    | `/venta/crearVenta`           | Crear una nueva venta.                |
| **PUT**     | `/cliente/editarCliente/{id}`       | Actualizar un cliente existente.        |
| **PUT**     | `/producto/editarProducto/{id}`       | Actualizar un producto existente.        |
| **PUT**     | `/venta/editarVenta/{id}`       | Actualizar una venta existente.        |
| **DELETE**  | `/cliente/eliminarCliente/{id}`         | Eliminar un cliente por su ID.           |
| **DELETE**  | `/producto/eliminarProducto/{id}`         | Eliminar un producto por su ID.           |
| **DELETE**  | `/venta/eliminarVenta/{id}`         | Eliminar una venta por su ID.           |

### 🔄 Ejemplos de JSON

#### 🧑‍🤝‍🧑 **Cliente**
```json
{
    "nombre": "Juan",
    "apellido": "Pérez",
    "dni": "12345678"
}
```

#### 📦 **Producto**
```json
{
    "nombre": "Taza",
    "marca": "Cerámicas S.A.",
    "costo": 250.00,
    "cantidad_disponible": 100
}
```

#### 🧾 **Venta**
```json
{
    "fecha_venta": "2024-10-18",
    "total": 500.00,
    "unCliente": {
        "id_cliente": 1
    },
    "listaProductos": [
        {
            "codigo_producto": 101
        },
        {
            "codigo_producto": 102
        }
    ]
}
```

---

## 🖼 Frontend - Thymeleaf y Bootstrap

El frontend utiliza **Thymeleaf** para renderizar dinámicamente las vistas en el servidor y **Bootstrap** para los estilos y la disposición responsiva. Además, se han integrado los **Bootstrap Icons** para mejorar la experiencia visual.

### 🌍 Páginas Implementadas

1. **Página Principal**: Muestra una bienvenida y un resumen de las funcionalidades del sistema.
2. **Gestión de Clientes**: Interfaz para listar, agregar y editar clientes.
3. **Gestión de Productos**: Vista para administrar los productos del bazar.
4. **Gestión de Ventas**: Página para la creación y seguimiento de ventas realizadas.

---

## 📂 Organización del Código

- **Controladores Thymeleaf (`controller`)**: Manejan las peticiones HTTP relacionadas con la visualización de páginas, renderizadas con Thymeleaf.
- **Controladores API Rest (`restcontroller`)**: Proveen acceso a la API REST, permitiendo realizar operaciones CRUD a través de solicitudes HTTP.

Cada controlador está bien separado para mantener la responsabilidad de manejar las solicitudes en sus respectivos contextos (vistas vs. API).

---

## 🧑‍💻 Cómo Contribuir

Si deseas contribuir al proyecto, sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama.
3. Realiza tus cambios y haz commits descriptivos.
4. Envía un pull request.

---

## 📞 Contacto

Si tienes alguna pregunta o sugerencia, no dudes en contactarme.
- [Agustin Minzoni (LinkedIn)](https://www.linkedin.com/in/agustinminzoni/)

---
