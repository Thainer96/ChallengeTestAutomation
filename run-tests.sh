#!/bin/bash
echo "========================================"
echo " Challenge Test Automation - Full Suite"
echo "========================================"

echo ""
echo "[1/4] Running Karate API Tests..."
echo "----------------------------------------"
mvn clean test -pl karateapi
KARATE_RESULT=$?

echo ""
echo "[2/4] Running Serenity BDD Tests..."
echo "----------------------------------------"
mvn clean verify -pl frontendautomation/serenitybdd
SERENITY_RESULT=$?

echo ""
echo "[3/4] Running K6 Smoke Test..."
echo "----------------------------------------"
k6 run k6performance/src/test/k6/login-smoke-test.js
K6_SMOKE=$?

if [ $K6_SMOKE -eq 0 ]; then
  echo ""
  echo "[4/4] Running K6 Load Test..."
  echo "----------------------------------------"
  k6 run k6performance/src/test/k6/login-load-test.js
  K6_RESULT=$?
else
  echo ""
  echo "[4/4] SKIPPED - Smoke test failed, load test not executed"
  K6_RESULT=1
fi

echo ""
echo "========================================"
echo " Results Summary"
echo "========================================"
if [ $KARATE_RESULT -eq 0 ]; then echo " Karate API:     PASSED"; else echo " Karate API:     FAILED"; fi
if [ $SERENITY_RESULT -eq 0 ]; then echo " Serenity BDD:   PASSED"; else echo " Serenity BDD:   FAILED"; fi
if [ $K6_SMOKE -eq 0 ]; then echo " K6 Smoke:       PASSED"; else echo " K6 Smoke:       FAILED"; fi
if [ $K6_RESULT -eq 0 ]; then echo " K6 Load:        PASSED"; else echo " K6 Load:        FAILED"; fi
echo "========================================"

echo ""
echo "Reports:"
echo " Karate:   karateapi/target/karate-reports/karate-summary.html"
echo " Serenity: frontendautomation/serenitybdd/target/site/serenity/index.html"
echo " K6 Smoke: k6performance/results/login-smoke-summary.json"
echo " K6 Load:  k6performance/results/login-load-summary.json"

exit $((KARATE_RESULT + SERENITY_RESULT + K6_RESULT))
