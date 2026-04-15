function fn() {
  karate.configure('ssl', true);
  karate.configure('connectTimeout', 10000);
  karate.configure('readTimeout', 10000);

  var env = karate.env;
  karate.log('karate.env system property was:', env);

  if (!env) {
    env = 'dev';
  }

  var config = {
    env: env,
    baseUrl: 'https://petstore.swagger.io/v2'
  };

  if (env === 'qa') {
    config.baseUrl = 'https://petstore.swagger.io/v2';
  }

  return config;
}
