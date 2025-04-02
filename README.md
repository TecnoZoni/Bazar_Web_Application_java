# 📦 Proyecto Bazar - Sistema de Gestión

Este proyecto es un sistema de gestión de clientes, productos y ventas para un bazar. Utiliza una estructura **monolítica** bajo el patrón **MVC (Modelo-Vista-Controlador)**, desarrollado con **Java 17**, **Spring Boot 3.3.5**, y conexión a una base de datos **H2** en memoria. La aplicación está dividida en dos partes:

1. **API Rest pública**: Documentada con **Swagger** y accesible sin autenticación.
2. **Aplicación web con Thymeleaf**: Requiere autenticación para acceder a las interfaces CRUD de clientes, productos y ventas.

Al iniciarse, la aplicación crea automáticamente un usuario administrador para facilitar su uso.

---

## 🛠 Tecnologías Utilizadas

- **Java 17**: Lenguaje de programación principal del backend.
- **Spring Boot 3.3.5**: Framework para la creación de aplicaciones empresariales con enfoque en simplicidad y rapidez.
- **H2 Database**: Base de datos en memoria utilizada para almacenar información de clientes, productos y ventas.
- **Thymeleaf**: Motor de plantillas usado para renderizar las vistas HTML dinámicamente desde el backend.
- **Bootstrap 5**: Framework de CSS para el diseño de una interfaz web moderna y responsiva.
- **Swagger**: Herramienta para la documentación de la API pública.
- **MapStruct**: Framework para el mapeo de entidades de la API.

---

## 🌐 Arquitectura del Proyecto

El proyecto sigue el patrón **MVC (Modelo-Vista-Controlador)**, dividiendo la lógica en las siguientes capas:

1. **Controller API (`controller.api`)**: Controladores dedicados a la API Rest pública.
2. **Controller Web (`controller.web`)**: Controladores para la aplicación web, requieren autenticación.
3. **Model (`model`)**: Clases de entidad que representan los datos de la aplicación.
4. **DTO (`dto`)**: Objetos de transferencia de datos utilizados para estructurar la información.
5. **Mapper (`mapper`)**: MapStruct se utiliza para la conversión entre entidades y DTOs.
6. **Repository (`repository`)**: Interfaces para acceso a datos.
7. **Service (`service`)**: Lógica de negocio.
8. **Configuración de seguridad (`security.config`)**: Gestión de autenticación y autorización.

### 📁 Estructura de Carpetas

```bash
src/
├── main/
│   ├── java/
│   │   ├── com/bazar/BazarApplication.java  # Punto de entrada de la aplicación
│   │   ├── com/bazar/config/                # Configuraciones generales
│   │   ├── com/bazar/controller/api/        # Controladores para la API pública
│   │   ├── com/bazar/controller/web/        # Controladores para la aplicación web (requiere login)
│   │   ├── com/bazar/dto/                   # Objetos de transferencia de datos
│   │   ├── com/bazar/mapper/                 # MapStruct para conversión de entidades
│   │   ├── com/bazar/model/                 # Clases del modelo (Cliente, Producto, Venta)
│   │   ├── com/bazar/repository/            # Repositorios para acceso a datos
│   │   ├── com/bazar/security/config/       # Configuración de seguridad
│   │   ├── com/bazar/service/               # Servicios de negocio
│   └── resources/
│       ├── templates/                        # Vistas HTML Thymeleaf
│       │   ├── components/                   # Navbar y otros componentes reutilizables
│       │   ├── acces_denied.html             # Vista para acceso denegado
│       │   ├── index.html                    # Página principal con botones a Swagger y opciones de admin
│       │   ├── login.html                    # Formulario de inicio de sesión
│       │   ├── cliente.html                  # Tabla de clientes con opciones CRUD
│       │   ├── cliente_form.html             # Formulario para crear un cliente
│       │   ├── producto.html                 # Tabla de productos con opciones CRUD
│       │   ├── producto_form.html            # Formulario para crear un producto
│       │   ├── venta.html                    # Tabla de ventas con opciones CRUD
│       │   ├── venta_form.html               # Formulario para crear una venta
│       │   ├── venta_form_editar.html        # Formulario para editar una venta (sin modal)
│       ├── application.properties            # Configuración de la base de datos y otras propiedades
```

---

## 🌟 Características del Proyecto

### ✅ Funcionalidades Principales

- **API Rest Pública**: Documentada con Swagger, permite gestionar clientes, productos y ventas.
- **Autenticación con Spring Security**: La aplicación web requiere iniciar sesión para acceder a las vistas de administración.
- **Gestión de Clientes, Productos y Ventas**: CRUD completo disponible en la aplicación web.
- **Creación Automática de Usuario Administrador**: La aplicación genera un usuario admin al iniciarse para facilitar las pruebas.

---

## 🖥 Cómo Iniciar el Proyecto

1. **Clonar el Repositorio**:

   ```bash
   git clone https://github.com/usuario/proyecto-bazar.git
   ```

2. **Ejecutar la Aplicación**:

   ```bash
   ./mvnw spring-boot:run
   ```

3. **Acceder a la Aplicación**:

   - **Interfaz Web (Thymeleaf)**: [http://localhost:8080](http://localhost:8080)
   - **API Rest - Swagger**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 🖼 Interfaz de Usuario

El frontend utiliza **Thymeleaf** y **Bootstrap**. La aplicación cuenta con un navbar como único componente reutilizable, donde se muestra un botón para iniciar sesión.

### **Páginas Implementadas**

1. **Página Principal (`index.html`)**: Información del proyecto y acceso a Swagger.
![image](https://github.com/user-attachments/assets/96d98874-a7d6-4efe-bdfe-b851fcc0fa6a)

2. **Formulario de Login (`login.html`)**.
![image](https://github.com/user-attachments/assets/15b544ad-56eb-48be-9605-8a8f5c4992e0)

3. **Gestión de Clientes (`cliente.html`)**: Tabla con acciones CRUD.
![image](https://github.com/user-attachments/assets/ba85b223-b660-4ba3-9d1e-23e88c044dc7)

![image](https://github.com/user-attachments/assets/3f7fb5cf-bd8c-4a81-b631-08b184abcb41)

5. **Formulario para Crear Cliente (`cliente_form.html`)**.
![image](https://github.com/user-attachments/assets/c8cd3916-c8e9-445a-8c44-e94e743b2109)

6. **Gestión de Productos (`producto.html`)**: Tabla con acciones CRUD.
![image](https://github.com/user-attachments/assets/4cdf726a-e849-4878-ba29-dc213849c30e)

![image](https://github.com/user-attachments/assets/85f1e531-2970-4cc3-9270-5c169b26df6c)

7. **Formulario para Crear Producto (`producto_form.html`)**.
![image](https://github.com/user-attachments/assets/b17da119-9d3e-4eee-90a7-d4d47113ac6e)

8. **Gestión de Ventas (`venta.html`)**: Tabla con acciones CRUD.
![image](https://github.com/user-attachments/assets/e5c70245-7156-4f1d-bc5a-8748a2e951ad)

9. **Formulario para Crear Venta (`venta_form.html`)**.
![image](https://github.com/user-attachments/assets/bbc96549-6117-45c0-9f88-16f1dd08bee5)

10. **Formulario para Editar Venta (`venta_form_editar.html`)**.
![image](https://github.com/user-attachments/assets/0f340fcf-ae45-4735-bde7-7d4d855c8722)


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
