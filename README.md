# Challenge Test Automation

Proyecto multi-módulo de QA Automation con tres soluciones independientes.

## Estructura del Proyecto

```
ChallengeTestAutomation/
├── karateapi/                          # API Testing con Karate DSL
│   └── src/test/
├── frontendautomation/                 # Frontend / UI Testing
│   ├── serenitybdd/                    # Serenity BDD + Cucumber + WebDriver
│   │   └── src/test/
│   ├── playwright/                     # Playwright para Java
│   │   └── src/test/
│   └── pom.xml
├── k6performance/                      # Performance Testing con K6
│   └── src/test/k6/
├── .gitignore
├── README.md
└── pom.xml                             # POM padre (multi-módulo)
```

## Módulos

### Karate API
API testing usando [Karate DSL](https://github.com/karatelabs/karate).

```bash
mvn test -pl karateapi
```

### Frontend Automation

#### Serenity BDD
UI testing con [Serenity BDD](https://serenity-bdd.info/) + Cucumber + WebDriver.

```bash
mvn verify -pl frontendautomation/serenitybdd
```

#### Playwright
UI testing con [Playwright para Java](https://playwright.dev/java/).

```bash
mvn test -pl frontendautomation/playwright
```

### K6 Performance
Performance testing usando [K6](https://k6.io/).

```bash
# Smoke test
k6 run k6performance/src/test/k6/smoke-test.js

# Load test
k6 run k6performance/src/test/k6/load-test.js

# Stress test
k6 run k6performance/src/test/k6/stress-test.js
```

## Requisitos

- Java 17+
- Maven 3.8+
- K6 (para tests de performance)

## Setup Inicial

```bash
# Instalar dependencias Maven
mvn clean install -DskipTests

# Instalar browsers de Playwright
mvn exec:java -pl frontendautomation/playwright -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"
```
