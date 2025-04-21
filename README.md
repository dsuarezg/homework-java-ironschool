# IronSchool

## Introducción

Este proyecto consiste en construir un sistema de gestión escolar llamado **IronSchool**, que permite administrar estudiantes, profesores y cursos con funcionalidades básicas.

## Requisitos previos

- **Java 21.**
- **Maven y dependencias.**

## Instrucciones de uso

1. **Clonar el repositorio**:
   Clona este repositorio en tu máquina local utilizando el siguiente comando:
   ```bash
   git clone https://github.com/dsuarezg/java-ironbattle
   ```

2. **Compilar el proyecto**:
   Asegúrate de tener **Maven** instalado. Para compilar el proyecto, ejecuta:
   ```bash
   mvn clean install
   ```

3. **Ejecutar la aplicación**:
   Para iniciar la aplicación, utiliza el siguiente comando:
   ```bash
   mvn exec:java -Dexec.mainClass="appMain"
   ```

4. **Pruebas unitarias**:
   Ejecuta las pruebas unitarias con:
   ```bash
   mvn test
   ```

### Flujo de la aplicación

1. Al iniciar, el sistema mostrará el menú principal.
2. Selecciona una opción ingresando el número correspondiente.
3. Sigue las instrucciones en pantalla para ingresar datos o ejecutar comandos.


## Menús de la aplicación

### Menú principal

Al iniciar la aplicación, se mostrará el siguiente menú principal:

```text
======== 🏫 Iron School ========
1. 🎓 Initialize School
2. 📚 Create individual entities
3. 🖥️ Use accepted commands
4. 📂 Load data from file
5. 💾 Save data to file
---------------------------------
6. ❌ Exit
=================================
Choose an option:
```

### Submenú

Si seleccionas la opción **2. Create individual entities**, se mostrará el siguiente submenú:

```text
======== 🏫 Iron School ========
1. 🧑‍🎓 Add new Student
2. 👩‍🏫 Add new Teacher
3. 📘 Add new Course
---------------------------------
4. 🔙 Return to main menu
=================================
Choose an option:
```

### Mensajes de interacción

Durante la ejecución, la aplicación mostrará mensajes como:

- `How many courses do you want to add?`
- `How many students do you want to add?`
- `Press Enter to continue...`
- `Invalid option. Please try again.`
- `Incorrect argument: [error message]`
- `Element not found: [error message]`
- `An unexpected error occurred: [error message]`

## Ejemplos de uso

### Comandos disponibles

- **ENROLL [STUDENT_ID] [COURSE_ID]**: Inscribir un estudiante en un curso.
  **Ejemplo**:
  ```text
  ENROLL b123 a1b2
  ```
  Esto inscribirá al estudiante con ID `b123` en el curso con ID `a1b2`.

- **ASSIGN [TEACHER_ID] [COURSE_ID]**: Asignar un profesor a un curso.
  **Ejemplo**:
  ```text
  ASSIGN 35a6 a1b2
  ```
  Esto asignará al profesor con ID `35a6` al curso con ID `a1b2`.

- **SHOW COURSES**: Mostrar todos los cursos disponibles.
  **Ejemplo**:
  ```text
  SHOW COURSES
  ```

- **LOOKUP COURSE [COURSE_ID]**: Mostrar detalles de un curso específico.
  **Ejemplo**:
  ```text
  LOOKUP COURSE a1b2
  ```

- **SHOW STUDENTS**: Mostrar todos los estudiantes registrados.
  **Ejemplo**:
  ```text
  SHOW STUDENTS
  ```

- **LOOKUP STUDENT [STUDENT_ID]**: Mostrar detalles de un estudiante específico.
  **Ejemplo**:
  ```text
  LOOKUP STUDENT b123
  ```

- **SHOW TEACHERS**: Mostrar todos los profesores registrados.
  **Ejemplo**:
  ```text
  SHOW TEACHERS
  ```

- **LOOKUP TEACHER [TEACHER_ID]**: Mostrar detalles de un profesor específico.
  **Ejemplo**:
  ```text
  LOOKUP TEACHER 35a6
  ```

- **SHOW PROFIT**: Calcular las ganancias totales de la escuela.
  **Ejemplo**:
  ```text
  SHOW PROFIT
  ```

## Notas importantes

- Todos los IDs (de estudiantes, profesores y cursos) son generados automáticamente.
- Los datos de ejemplo se encuentran en los archivos `src/main/resources/*.json`.


## Contacto

Para cualquier duda o problema, por favor contacta al equipo de desarrollo.
