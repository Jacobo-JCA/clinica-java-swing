<h2 align="center">Consultorio Médico – Sistema de Gestión del Historial del Paciente</h2>

###  Descripción del proyecto
Este proyecto tiene como objetivo permitir al usuario gestionar el historial clínico de un paciente, centralizando la información relacionada con sus consultas médicas, signos vitales y estado general de salud.
El sistema está pensado desde la perspectiva de un consultorio médico, garantizando que el médico tenga acceso inmediato al historial del paciente de forma cronológica y estructurada.

---

###  Contexto del dominio
Modele el dominio a partir de como el médico registra la informacion durante la cita médica.

- Gestiona pacientes y su información personal.
- Registra individualmente un paciente pero luego puede asociarlo a la consulta y estado de salud.
- Almacena cada consulta de manera independiente y posteriormente la asocia al paciente.
- El médico solo registra los signos vitales una vez por cita y estos quedan asociados a la consulta.
- El estado de salud del paciente se maneja como un historial clínico general.
- No se utiliza un historial de enfermedades estructurado; en su lugar, el sistema permite registrar el **Health Status** para campos mas generales, priorizando flexibilidad y simplicidad.

---

###  Modelo de datos (Base de Datos)
El modelo de base de datos que diseñé fue a partir del dominio previamente definido.

- La relación 1:1 se implementa colocando la FK en SignosVitales apuntando a Consulta, con restricción UNIQUE. Esto garantiza que cada consulta tenga exactamente un registro de signos vitales y evita múltiples registros se asocien a la misma consulta.
- Las relaciones de tipo 1:N se implementan colocando la FK en las tablas dependientes (Consultation y Health_Status). Esto garantiza que un mismo paciente pueda tener múltiples registros asociados: varias consultas a lo largo del tiempo y múltiples estados de salud. De esta forma, la DB asegura integridad referencial y permite mantener un historial completo sin duplicar información en la tabla principal de pacientes.

 ---

###  Arquitectura del sistema

En el proyecto utilicé una **arquitectura limpia (Clean Architecture)**, organizada de la siguiente manera:
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

Elegí la arquitectura limpia con el objetivo de:

- Poder separar claramente las responsabilidades de cada componente.
- Aislar la lógica de negocio del acceso a datos y de la capa de presentación.
- Facilitar el mantenimiento y la evolución del sistema.
- Permitir que los cambios en una capa tengan el menor impacto posible en las demás.

Internamente, el proyecto sigue el patrón **MVC** de forma distribuida, ya que es un patrón de presentacion:
- El **Model** corresponde a las entidades y reglas de negocio ubicadas en la capa de dominio.
- La **View** y el **Controller** forman parte de la capa de presentación, encargada de la interacción con el usuario.

Esta estructura me permite tener un diseño limpio, entendible y alineado con buenas prácticas de desarrollo backend.

---

### ⚙️ Tecnologías

- **Java 17**
- **Java Swing**
- **PostgreSQL**
- **NetBeans 17**
- **MySQL Workbench**



