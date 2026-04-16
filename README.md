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
API testing usando [Karate DSL](https://github.com/karatelabs/karate) contra la API de [PetStore](https://petstore.swagger.io/).

```
karateapi/src/test/
├── java/runner/
│   ├── UserRunner.java           # Runner por dominio
│   └── ParallelRunner.java       # Runner paralelo (5 threads)
└── resources/
    ├── karate-config.js
    └── features/user/
        ├── data/                  # Payloads JSON externalizados
        ├── create-user.feature
        ├── search-user.feature
        ├── update-user.feature
        ├── delete-user.feature
        └── negative-user.feature
```

Ejecucion:

```bash
# Todos los tests
mvn test -pl karateapi

# Solo el runner de usuario
mvn test -pl karateapi -Dtest=runner.UserRunner

# Ejecucion paralela (5 threads)
mvn test -pl karateapi -Dtest=runner.ParallelRunner

# Por tags
mvn test -pl karateapi -Dkarate.options="--tags @smoke"
mvn test -pl karateapi -Dkarate.options="--tags @negative"
mvn test -pl karateapi -Dkarate.options="--tags @create"
mvn test -pl karateapi -Dkarate.options="--tags @update"
mvn test -pl karateapi -Dkarate.options="--tags @delete"

# Limpiar y ejecutar
mvn clean test -pl karateapi
```

Reportes:

```
karateapi/target/karate-reports/karate-summary.html     # Reporte general
karateapi/target/karate-reports/karate-timeline.html     # Timeline de ejecucion paralela
```

### Frontend Automation

#### Serenity BDD
UI testing con [Serenity BDD](https://serenity-bdd.info/) + Cucumber + Screenplay Pattern contra [OpenCart](http://opencart.abstracta.us/).

Patron Screenplay: Actor → Task → Question → Page (Targets)

```
frontendautomation/serenitybdd/src/test/
├── java/opencart/
│   ├── pages/                     # Targets (localizadores de UI)
│   │   ├── HomePage.java
│   │   ├── SearchResultPage.java
│   │   ├── CartPage.java
│   │   └── CheckoutPage.java
│   ├── tasks/                     # Acciones del Actor
│   │   ├── OpenTheStore.java
│   │   ├── AddProductToCart.java
│   │   ├── ViewTheCart.java
│   │   ├── ProceedToGuestCheckout.java
│   │   ├── FillBillingDetails.java
│   │   └── ConfirmTheOrder.java
│   ├── questions/                 # Validaciones
│   │   ├── TheCartItemCount.java
│   │   └── TheOrderConfirmationMessage.java
│   ├── stepdefinitions/           # Glue de Cucumber
│   │   ├── PurchaseStepDefinitions.java
│   │   └── Hooks.java
│   └── runners/
│       └── TestRunner.java
└── resources/
    ├── features/compra/
    │   └── flujo_compra.feature   # Feature en español
    ├── serenity.conf              # Config multi-browser
    ├── logback-test.xml
    ├── cucumber.properties
    └── junit-platform.properties
```

Ejecucion:

```bash
# Ejecutar todos los tests + reporte Serenity
mvn verify -pl frontendautomation/serenitybdd

# Solo ejecutar tests sin reporte
mvn test -pl frontendautomation/serenitybdd

# Filtrar por tags
mvn verify -pl frontendautomation/serenitybdd -Dcucumber.filter.tags="@e2e"
mvn verify -pl frontendautomation/serenitybdd -Dcucumber.filter.tags="@compra"

# Cambiar navegador
mvn verify -pl frontendautomation/serenitybdd -Dwebdriver.driver=firefox
mvn verify -pl frontendautomation/serenitybdd -Dwebdriver.driver=edge

# Modo headless
mvn verify -pl frontendautomation/serenitybdd -Dheadless.mode=true

# Cambiar ambiente
mvn verify -pl frontendautomation/serenitybdd -Denvironment=qa
```

Reportes:

```
frontendautomation/serenitybdd/target/site/serenity/index.html
```

#### Playwright
UI testing con [Playwright para Java](https://playwright.dev/java/).

```bash
mvn test -pl frontendautomation/playwright
```

### K6 Performance
Prueba de carga del servicio de login de [FakeStoreAPI](https://fakestoreapi.com/) usando [K6](https://k6.io/).

```
k6performance/src/test/k6/
├── data/
│   └── login-users.csv            # Credenciales parametrizadas
├── login-smoke-test.js            # Smoke: valida que el servicio este arriba
└── login-load-test.js             # Load: prueba de carga con thresholds
```

Thresholds configurados (load test):
- Response time: p(95) < 1.5 segundos
- Error rate: < 3%
- Throughput: >= 20 TPS

Metricas custom: `login_duration` (Trend), `login_fail_rate` (Rate), `login_success_count` (Counter)

Ejecucion:

```bash
# Smoke test (1 VU, 1 iteracion - valida que el servicio responda)
k6 run k6performance/src/test/k6/login-smoke-test.js

# Load test (ramp-up hasta 25 VUs)
k6 run k6performance/src/test/k6/login-load-test.js
```

Estrategia recomendada: ejecutar smoke primero, si pasa ejecutar load. Los scripts `run-tests.bat` y `run-tests.sh` ya implementan esta logica automaticamente.

Reportes:

```
k6performance/results/login-smoke-summary.json   # Resultado del smoke test
k6performance/results/login-load-summary.json    # Resultado del load test
```

## Requisitos

- Java 17+
- Maven 3.8+
- K6 (para tests de performance)

> **Windows (PowerShell):** Los parametros Maven con punto deben ir entre comillas: `"-Dwebdriver.driver=firefox"`, `"-Dheadless.mode=true"`

## Setup Inicial

```bash
# Limpiar y compilar todo el proyecto
mvn clean install -DskipTests

# Instalar browsers de Playwright
mvn exec:java -pl frontendautomation/playwright -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"
```

## Ejecucion completa (Full Suite)

Scripts que ejecutan Karate API + Serenity BDD + K6 Performance en secuencia y muestran un resumen con resultados y rutas a reportes.

```bash
# Windows (CMD)
.\run-tests.bat

# Mac / Linux
chmod +x run-tests.sh
./run-tests.sh

# O con Maven directo (ambos modulos en un comando)
mvn clean verify -pl karateapi,frontendautomation/serenitybdd
```
