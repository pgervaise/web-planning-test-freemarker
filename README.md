# web-planning-test-freemarker
Testing using Freemarker with Spring Boot 2.3.1. Each time a web page is loaded there is a warning in the console log.

The main class of spring boot app is :

    fr.penelope.planning.web.WebPlanningApplication
    
Then access with :

    http://localhost:8080/

It shows "APP OK !" in the web browser but there is a warning message in console log :

    BeansWrapper.incompatibleImprovements was set to the object returned by Configuration.getVersion(). That defeats the purpose of incompatibleImprovements, and makes upgrading FreeMarker a potentially breaking change. Also, this probably won't be allowed starting from 2.4.0. Instead, set incompatibleImprovements to the highest concrete version that's known to be compatible with your application.


The responsible class is *fr.penelope.planning.web.config.freemarker.FreemarkerConfig* when generating a *FreeMarkerViewResolver* object.

With Spring Boot 2.2.5, it's OK but starting with Spring Boot 2.2.8 it's not OK. It's because upgrading Freemarker to version 2.3.30+.
