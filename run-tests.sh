#!/bin/bash
echo "========================================"
echo " Challenge Test Automation - Full Suite"
echo "========================================"

echo ""
echo "[1/2] Running Karate API Tests..."
echo "----------------------------------------"
mvn clean test -pl karateapi
KARATE_RESULT=$?

echo ""
echo "[2/2] Running Serenity BDD Tests..."
echo "----------------------------------------"
mvn clean verify -pl frontendautomation/serenitybdd
SERENITY_RESULT=$?

echo ""
echo "========================================"
echo " Results Summary"
echo "========================================"
if [ $KARATE_RESULT -eq 0 ]; then echo " Karate API:   PASSED"; else echo " Karate API:   FAILED"; fi
if [ $SERENITY_RESULT -eq 0 ]; then echo " Serenity BDD: PASSED"; else echo " Serenity BDD: FAILED"; fi
echo "========================================"

echo ""
echo "Reports:"
echo " Karate:   karateapi/target/karate-reports/karate-summary.html"
echo " Serenity: frontendautomation/serenitybdd/target/site/serenity/index.html"

exit $((KARATE_RESULT + SERENITY_RESULT))
