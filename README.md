<h2 align="center">Consultorio Médico – Sistema de Gestión del Historial del Paciente</h2>

### 📌 Descripción del proyecto
Este proyecto tiene como objetivo permitir al usuario gestionar el historial clínico de un paciente, centralizando la información relacionada con sus consultas médicas, signos vitales y estado general de salud.
El sistema está pensado desde la perspectiva de un consultorio médico, donde el enfoque está en el seguimiento del estado de salud del paciente a lo largo del tiempo.

---

### 🧠 Contexto del dominio
El dominio se modela a partir de como el médico registra la informacion durante la cita médica.

- El sistema gestiona pacientes y su información personal.
- Un paciente puede tener múltiples consultas a lo largo del tiempo.
- Las consultas se registran de forma independiente, luego se asocian al paciente y representan información de la atención médica.
- Los signos vitales se registran una sola vez por consulta y se asocian posteriormente.
- El estado de salud del paciente se maneja como un historial clínico general.
- No se utiliza un historial de enfermedades estructurado; en su lugar, el sistema permite registrar el **Health Status** para campos mas generales, priorizando flexibilidad y simplicidad.

---

### 🗄️ Modelo de datos (Base de Datos)
El modelo de base de datos se diseñó a partir del dominio previamente definido.
Algunas decisiones clave del modelado:

- La entidad **Health_Status** no representa enfermedades específicas (como gripe o diabetes), sino el estado general de salud(enfermedades) del paciente.
- Los signos vitales están asociados a una consulta específica y se registran una sola vez, reflejando el flujo real de atención en un consultorio médico.
- El historial del paciente puede consultarse de forma completa, incluyendo:
  - Datos del paciente
  - Consultas realizadas
  - Signos vitales por consulta
  - Estado de salud general

 ---

### 🏗️ Arquitectura del sistema

El proyecto utiliza una **arquitectura en capas (Layered Architecture)**, organizada de la siguiente manera:
```text
┌───────────────────────────────┐
│        Presentation           │
│  Controllers  / View          │
└───────────────▲───────────────┘
                │
┌───────────────┴───────────────┐
│        Application            │
│  Use Cases / Services         │
└───────────────▲───────────────┘
                │
┌───────────────┴───────────────┐
│           Domain              │
│  Entities / Business Logic    │
└───────────────▲───────────────┘
                │
┌───────────────┴───────────────┐
│       Infrastructure          │
│  Persistence / DB / External  │
└───────────────────────────────┘
```

#### Justificación de la arquitectura

Se eligió una arquitectura en capas con el objetivo de:

- Separar claramente las responsabilidades de cada componente.
- Aislar la lógica de negocio del acceso a datos y de la capa de presentación.
- Facilitar el mantenimiento y la evolución del sistema.
- Permitir que los cambios en una capa tengan el menor impacto posible en las demás.

Internamente, el proyecto sigue el patrón **MVC**, de forma distribuida, ya que es un patrón de presentacion:
- El **Model** corresponde a las entidades y reglas de negocio ubicadas en la capa de dominio.
- La **View** y el **Controller** forman parte de la capa de presentación, encargada de la interacción con el usuario.

Esta estructura favorece un diseño limpio, entendible y alineado con buenas prácticas de desarrollo backend.

---

### ⚙️ Tecnologías

- **Java 17**
- **Java Swing**
- **PostgreSQL**
- **NetBeans 17**
- **MySQL Workbench**



