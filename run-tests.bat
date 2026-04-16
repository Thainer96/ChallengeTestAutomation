@echo off
echo ========================================
echo  Challenge Test Automation - Full Suite
echo ========================================

echo.
echo [1/2] Running Karate API Tests...
echo ----------------------------------------
call mvn clean test -pl karateapi
set KARATE_RESULT=%ERRORLEVEL%

echo.
echo [2/2] Running Serenity BDD Tests...
echo ----------------------------------------
call mvn clean verify -pl frontendautomation/serenitybdd
set SERENITY_RESULT=%ERRORLEVEL%

echo.
echo ========================================
echo  Results Summary
echo ========================================
if %KARATE_RESULT%==0 (echo  Karate API:   PASSED) else (echo  Karate API:   FAILED)
if %SERENITY_RESULT%==0 (echo  Serenity BDD: PASSED) else (echo  Serenity BDD: FAILED)
echo ========================================

echo.
echo Reports:
echo  Karate:  karateapi\target\karate-reports\karate-summary.html
echo  Serenity: frontendautomation\serenitybdd\target\site\serenity\index.html

exit /b %KARATE_RESULT%
