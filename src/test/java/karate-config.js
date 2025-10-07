function fn() {

  var baseUrl = ''
  var baseUrlQa = ''
  var env = karate.env; // get system property 'karate.env'
  karate.configure('ssl',true)
  karate.log('karate.env system property was:', env);
  if (!env) {
    env = 'dev';
  }

  if (env == 'dev') {
     baseUrl = 'https://petstore.swagger.io/v2/'
     baseUrlQa = 'https://google.com/'
    // customize
    // e.g. config.foo = 'bar';
  } else if (env == 'test') {
    baseUrl = 'https://petstore.swagger.io/v2/'
    baseUrlQa = 'https://bensg.com/test-qalab/'
    // customize
  }
  var config = {
    baseUrlQa:baseUrlQa,
       baseUrl:baseUrl,
      env: env,
      myVarName: 'someValue'
    }
  return config;
}