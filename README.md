[PL]

# ProjectMVC-SpringBoot-Recipes-App

Aplikacja webowa do zarządzania przepisami kulinarnymi, stworzona w architekturze MVC z wykorzystaniem Spring Boot. Umożliwia przeglądanie, dodawanie, edytowanie i usuwanie przepisów, a także komentowanie oraz dodawanie przepisów do własnej książki kucharskiej.

---

## Technologie

- Spring Boot – główny framework aplikacji
- Spring MVC – obsługa wzorca Model-View-Controller
- Spring Security – uwierzytelnianie i autoryzacja użytkowników
- Spring Data JPA – integracja z bazą danych
- Thymeleaf – szablony HTML dla warstwy widoku
- Hibernate – ORM dla mapowania encji
- H2 – wbudowana baza danych (do testów)
- Bootstrap – stylizacja interfejsu użytkownika

---

## Funkcje

- Rejestracja i logowanie użytkowników
- Dodawanie, edytowanie i usuwanie przepisów
- Dodawanie komentarzy do przepisów
- Tworzenie własnej książki kucharskiej
- Filtrowanie przepisów po nazwie, składnikach, czasie przygotowania itp.
- Zabezpieczenie dostępu do funkcji za pomocą Spring Security

---

## Uruchamianie aplikacji lokalnie

### Wymagania

- Java 17+
- Maven 3.8+

### Kroki

1. Sklonuj repozytorium:
   ```bash
   git clone https://github.com/ITbartH/ProjectMVCSpringboot-Recipes-App.git
   cd ProjectMVCSpringboot-Recipes-App
   ```

2. Uruchom aplikację:
   ```bash
   mvn spring-boot:run
   ```

3. Otwórz przeglądarkę i przejdź do: [http://localhost:8080](http://localhost:8080)

---

## Dane testowe

Aplikacja zawiera przykładowe dane testowe ładowane przy starcie. Możesz je dostosować w pliku `data.sql` lub za pomocą interfejsu użytkownika.

---

## Struktura katalogów

```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── recipesapp
│   │   │               ├── controller
│   │   │               ├── model
│   │   │               ├── repository
│   │   │               ├── service
│   │   │               └── security
│   └── resources
│       ├── templates
│       └── static
└── pom.xml
```

---

## Uwierzytelnianie

Aplikacja wykorzystuje Spring Security do zarządzania użytkownikami. Nowi użytkownicy mogą się rejestrować, a następnie logować, aby uzyskać dostęp do funkcji takich jak dodawanie przepisów czy komentarzy.


---

## Licencja

Ten projekt jest objęty licencją MIT. Szczegóły znajdują się w pliku LICENSE.

---

## Współpraca

Zachęcam do zgłaszania błędów, sugestii oraz propozycji nowych funkcji poprzez zakładkę [Issues](https://github.com/ITbartH/ProjectMVCSpringboot-Recipes-App/issues).

---

Autor: [ITbartH](https://github.com/ITbartH)

[ENG]

# ProjectMVC-SpringBoot-Recipes-App

A web application for managing cooking recipes, built using the MVC architecture with Spring Boot. It allows users to browse, add, edit, and delete recipes, as well as comment on and save them to their personal cookbook.

---

## Technologies

- Spring Boot – main framework of the application
- Spring MVC – Model-View-Controller architecture
- Spring Security – user authentication and authorization
- Spring Data JPA – database integration
- Thymeleaf – HTML templating engine
- Hibernate – ORM for entity mapping
- H2 – embedded database (for testing)
- Bootstrap – user interface styling

---

## Features

- User registration and login
- Adding, editing, and deleting recipes
- Commenting on recipes
- Creating a personal cookbook
- Filtering recipes by name, ingredients, preparation time, etc.
- Access control using Spring Security

---

## Running the Application Locally

### Requirements

- Java 17+
- Maven 3.8+

### Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/ITbartH/ProjectMVCSpringboot-Recipes-App.git
   cd ProjectMVCSpringboot-Recipes-App
   ```

2. Run the application:
   ```bash
   mvn spring-boot:run
   ```

3. Open your browser and go to: [http://localhost:8080](http://localhost:8080)

---

## Test Data

The application includes sample test data that is loaded on startup. You can modify it in the `data.sql` file or use the web interface.

---

## Project Structure

```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── recipesapp
│   │   │               ├── controller
│   │   │               ├── model
│   │   │               ├── repository
│   │   │               ├── service
│   │   │               └── security
│   └── resources
│       ├── templates
│       └── static
└── pom.xml
```

---

## Authentication

The application uses Spring Security to manage users. New users can register and log in to access features like adding recipes and posting comments.

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Contribution

Feel free to submit issues, suggestions, or feature requests via the [Issues](https://github.com/ITbartH/ProjectMVCSpringboot-Recipes-App/issues) tab.

---

Author: [ITbartH](https://github.com/ITbartH)