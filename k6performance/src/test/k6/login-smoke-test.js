import http from 'k6/http';
import { check } from 'k6';
import { textSummary } from 'https://jslib.k6.io/k6-summary/0.0.2/index.js';

export const options = {
  vus: 1,
  iterations: 1,
  thresholds: {
    http_req_duration: ['p(95)<3000'],
    http_req_failed: ['rate==0'],
  },
};

export default function () {
  const payload = JSON.stringify({
    username: 'johnd',
    password: 'm38rmF$',
  });

  const params = {
    headers: { 'Content-Type': 'application/json' },
    timeout: '60s',
  };

  const res = http.post('https://fakestoreapi.com/auth/login', payload, params);

  const passed = check(res, {
    'service is up': (r) => r.status === 200 || r.status === 201,
    'response time < 3s': (r) => r.timings.duration < 3000,
    'response has token': (r) => {
      try {
        return JSON.parse(r.body).token !== undefined;
      } catch (e) {
        return false;
      }
    },
  });

  if (!passed) {
    console.error('SMOKE TEST FAILED - Service may be down. Aborting load test.');
  }
}

export function handleSummary(data) {
  return {
    'k6performance/results/login-smoke-summary.json': JSON.stringify(data, null, 2),
    stdout: textSummary(data, { indent: ' ', enableColors: true }),
  };
}
