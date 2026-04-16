@echo off
echo ========================================
echo  Challenge Test Automation - Full Suite
echo ========================================

echo.
echo [1/4] Running Karate API Tests...
echo ----------------------------------------
call mvn clean test -pl karateapi
set KARATE_RESULT=%ERRORLEVEL%

echo.
echo [2/4] Running Serenity BDD Tests...
echo ----------------------------------------
call mvn clean verify -pl frontendautomation/serenitybdd
set SERENITY_RESULT=%ERRORLEVEL%

echo.
echo [3/4] Running K6 Smoke Test...
echo ----------------------------------------
call k6 run k6performance/src/test/k6/login-smoke-test.js
set K6_SMOKE=%ERRORLEVEL%

if %K6_SMOKE%==0 (
  echo.
  echo [4/4] Running K6 Load Test...
  echo ----------------------------------------
  call k6 run k6performance/src/test/k6/login-load-test.js
  set K6_RESULT=%ERRORLEVEL%
) else (
  echo.
  echo [4/4] SKIPPED - Smoke test failed, load test not executed
  set K6_RESULT=1
)

echo.
echo ========================================
echo  Results Summary
echo ========================================
if %KARATE_RESULT%==0 (echo  Karate API:     PASSED) else (echo  Karate API:     FAILED)
if %SERENITY_RESULT%==0 (echo  Serenity BDD:   PASSED) else (echo  Serenity BDD:   FAILED)
if %K6_SMOKE%==0 (echo  K6 Smoke:       PASSED) else (echo  K6 Smoke:       FAILED)
if %K6_RESULT%==0 (echo  K6 Load:        PASSED) else (echo  K6 Load:        FAILED)
echo ========================================

echo.
echo Reports:
echo  Karate:      karateapi\target\karate-reports\karate-summary.html
echo  Serenity:    frontendautomation\serenitybdd\target\site\serenity\index.html
echo  K6 Smoke:    k6performance\results\login-smoke-summary.json
echo  K6 Load:     k6performance\results\login-load-summary.json

exit /b %KARATE_RESULT%
