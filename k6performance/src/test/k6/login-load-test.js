import http from 'k6/http';
import { check, sleep } from 'k6';
import { SharedArray } from 'k6/data';
import papaparse from 'https://jslib.k6.io/papaparse/5.1.1/index.js';
import { Trend, Rate, Counter } from 'k6/metrics';
import { textSummary } from 'https://jslib.k6.io/k6-summary/0.0.2/index.js';

const loginDuration = new Trend('login_duration');
const loginFailRate = new Rate('login_fail_rate');
const loginSuccess = new Counter('login_success_count');

const users = new SharedArray('login users', function () {
  return papaparse.parse(open('./data/login-users.csv'), { header: true }).data;
});

export const options = {
  stages: [
    { duration: '30s', target: 10 },
    { duration: '1m', target: 25 },
    { duration: '2m', target: 25 },
    { duration: '30s', target: 0 },
  ],
  thresholds: {
    http_req_duration: ['p(95)<1500'],
    http_req_failed: ['rate<0.03'],
    http_reqs: ['rate>=20'],
    login_duration: ['p(95)<1500'],
    login_fail_rate: ['rate<0.03'],
  },
};

export default function () {
  const user = users[__VU % users.length];

  const payload = JSON.stringify({
    username: user.user,
    password: user.passwd,
  });

  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
    timeout: '60s',
  };

  const res = http.post('https://fakestoreapi.com/auth/login', payload, params);

  loginDuration.add(res.timings.duration);

  const passed = check(res, {
    'status is 201': (r) => r.status === 201,
    'response time < 1500ms': (r) => r.timings.duration < 1500,
    'response has token': (r) => {
      try {
        return JSON.parse(r.body).token !== undefined;
      } catch (e) {
        return false;
      }
    },
  });

  loginFailRate.add(!passed);
  if (passed) loginSuccess.add(1);

  sleep(0.5);
}


export function handleSummary(data) {
  return {
    'results/login-load-summary.json': JSON.stringify(data, null, 2),
    stdout: textSummary(data, { indent: ' ', enableColors: true }),
  };
}
