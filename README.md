# Sistema de Gestión de Usuarios

## Descripción
Aplicación de escritorio desarrollada en Java con interfaz gráfica Swing para la gestión de usuarios. Utiliza Hibernate como ORM para la persistencia de datos y MySQL como base de datos.

## Tecnologías Utilizadas
- **Java 17**
- **Maven** - Gestión de dependencias
- **Hibernate 6.4.4** - Mapeo objeto-relacional
- **MySQL** - Base de datos
- **Swing** - Interfaz gráfica
- **FlatLaf** - Tema visual moderno
- **JUnit 5** - Pruebas unitarias

## Características
- ✅ CRUD completo de usuarios
- ✅ Validaciones de formulario
- ✅ Contraseña mínima de 12 caracteres (solo al crear usuario)
- ✅ Cambio de contraseña solo desde el botón/modal especial en edición
- ✅ La contraseña nunca se muestra ni se edita junto con los demás datos
- ✅ Borrado lógico (no físico): los usuarios eliminados no se muestran ni exportan
- ✅ Solo se muestran usuarios activos y no eliminados
- ✅ Exportación a Excel sin contraseña ni campo eliminado
- ✅ Interfaz gráfica moderna
- ✅ Arquitectura en capas (Vista-Controlador-Servicio-Entidad)
- ✅ Pruebas unitarias
- ✅ Pantalla completa
- ✅ Conexión a base de datos MySQL

## Requisitos Previos
- Java 17 o superior
- Maven 3.6+
- MySQL 8.0+
- IDE (Eclipse, IntelliJ IDEA, NetBeans)

## Instalación

### 1. Clonar el repositorio
```bash
git clone <url-del-repositorio>
cd mavenproject4
```

### 2. Configurar la base de datos
1. Crear una base de datos MySQL llamada `soa`
2. Ejecutar el script SQL para crear la tabla `tbl_users`:
```sql
CREATE TABLE `tbl_users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `rol_id` int DEFAULT NULL,
  `document_number` varchar(80) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `password` text,
  `email` varchar(100) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_active` tinyint DEFAULT '1',
  `deleted` tinyint DEFAULT '0',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

### 3. Configurar conexión a base de datos
Editar `src/main/resources/hibernate.cfg.xml`:
```xml
<property name="hibernate.connection.url">jdbc:mysql://localhost:3306/soa</property>
<property name="hibernate.connection.username">root</property>
<property name="hibernate.connection.password">tu_password</property>
```

### 4. Compilar y ejecutar
```bash
# Compilar el proyecto
mvn clean compile

# Ejecutar la aplicación
mvn exec:java -Dexec.mainClass="com.mycompany.mavenproject4.Views.Main"

# O crear el JAR ejecutable
mvn clean package
java -jar target/mavenproject4-1.0-SNAPSHOT.jar
```

## Uso de la Aplicación

### Interfaz Principal
- **Agregar**: Crear un nuevo usuario (pide contraseña y confirmación)
- **Editar**: Modificar usuario seleccionado (no permite cambiar contraseña)
- **Cambiar contraseña**: Botón/modal especial en edición para actualizar solo la contraseña
- **Eliminar**: Borrado lógico (no físico)
- **Refrescar**: Actualizar la tabla de usuarios
- **Exportar a Excel**: Exporta solo usuarios activos y no eliminados, sin contraseña ni campo eliminado

### Validaciones
- Nombre, apellido, email y contraseña (solo al crear) son obligatorios
- Contraseña mínima de 12 caracteres
- Email debe tener formato válido
- Rol solo puede ser Admin o Usuario

## Estructura del Proyecto
```
src/
├── main/
│   ├── java/
│   │   └── com/mycompany/mavenproject4/
│   │       ├── Views/           # Capa de presentación
│   │       │   └── Main.java    # Ventana principal
│   │       ├── service/         # Capa de servicios
│   │       │   └── UserService.java
│   │       ├── entities/        # Capa de entidades
│   │       │   └── User.java
│   │       └── database/        # Configuración de BD
│   │           └── HibernateUtil.java
│   └── resources/
│       └── hibernate.cfg.xml    # Configuración Hibernate
└── test/
    └── java/
        └── com/mycompany/mavenproject4/
            └── service/
                └── UserServiceTest.java
```

## Pruebas
Ejecutar las pruebas unitarias:
```bash
mvn test
```

Las pruebas incluyen:
- Crear usuario
- Obtener todos los usuarios
- Actualizar usuario
- Eliminar usuario

## Configuración de Desarrollo

### IDE Recomendado
- **IntelliJ IDEA**: Importar como proyecto Maven
- **Eclipse**: Importar proyecto Maven existente
- **NetBeans**: Abrir como proyecto Maven

### Configuración de Base de Datos
El proyecto está configurado para:
- **Host**: localhost
- **Puerto**: 3306
- **Base de datos**: soa
- **Usuario**: root
- **Contraseña**: (vacía por defecto)

## Configuración por Variables de Entorno

Puedes configurar la conexión a la base de datos usando variables de entorno:

- `DB_URL` — URL JDBC de la base de datos (por defecto: jdbc:mysql://localhost:3306/soa?useSSL=false&serverTimezone=UTC)
- `DB_USER` — Usuario de la base de datos (por defecto: root)
- `DB_PASS` — Contraseña de la base de datos (por defecto: vacío)

Ejemplo de ejecución con variables personalizadas:

```bash
DB_URL="jdbc:mysql://localhost:3306/soa" DB_USER="root" DB_PASS="mipass" mvn exec:java -Dexec.mainClass="com.mycompany.mavenproject4.Views.Main"
```

O para el JAR:

```bash
DB_URL="jdbc:mysql://localhost:3306/soa" DB_USER="root" DB_PASS="mipass" java -jar target/mavenproject4-1.0-SNAPSHOT.jar
```

## Troubleshooting

### Error de Conexión a Base de Datos
1. Verificar que MySQL esté ejecutándose
2. Confirmar credenciales en `