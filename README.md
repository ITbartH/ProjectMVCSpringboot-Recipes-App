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

## Demo

Aplikacja została wdrożona na Vercel i jest dostępna pod adresem: [project-mvc-springboot-recipes-app.vercel.app](https://project-mvc-springboot-recipes-app.vercel.app)

---

## Licencja

Ten projekt jest objęty licencją MIT. Szczegóły znajdują się w pliku LICENSE.

---

## Współpraca

Zachęcam do zgłaszania błędów, sugestii oraz propozycji nowych funkcji poprzez zakładkę [Issues](https://github.com/ITbartH/ProjectMVCSpringboot-Recipes-App/issues).

---

Autor: [ITbartH](https://github.com/ITbartH)
