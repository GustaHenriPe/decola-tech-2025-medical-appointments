# decola-tech-2025-medical-appointments
An API for medical appointments

``` mermaid
classDiagram
    class Doctor {
        +int id
        +String name
        +String specialty
    }

    class Patient {
        +int id
        +String name
        +String cpf
        +String cellphone
        +String email
        +Date dateOfBirth
    }

    class Consultation {
        +int id
        +int patientId
        +int doctorId
        +Datetime scheduledDateTime
        +String description
        +String status
    }

    Doctor "1" <-- "*" Consultation : attends
    Patient "1" <-- "*" Consultation : has
``` 
