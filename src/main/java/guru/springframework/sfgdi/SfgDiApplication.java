package guru.springframework.sfgdi;

import guru.springframework.sfgdi.controllers.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SfgDiApplication {

    public static void main(String[] args) {
        ApplicationContext ctx =  SpringApplication.run(SfgDiApplication.class, args);

        I18nController i18nController = (I18nController)ctx.getBean("i18nController");
        // change the spring.profiles.active entry between EN and ES in application.properties to play around with this.
        // or comment it out completely and see what happens.
        // spanish is used because the @Profile annotation on the I18NSpanishGreetingService has "ES" and "default"
        System.out.println(i18nController.sayHello());

        // notice that we aren't doing a new MyController(); anywhere.
        // We are just asking the spring framework to get us an instance of the controller.
        // the framework handles creating what is needed.
        MyController myController = (MyController)ctx.getBean("myController");

        System.out.println("-----------Primary Bean");
        // We have not added the @Qualifier annotation to MyController so it will end up using the PrimaryGreetingService because it is marked with the @Primary annotation.
        System.out.println(myController.sayHello());

        System.out.println("-----------Property Based DI");
        // we had to add the @Controller annotation to the PropertyInjectedController and @AutoWired to the greetingService property.
        // not really recommended to use this type of DI
        // We use the @Qualifier("propertyInjectedGreetingService") to force it to use the PropertyInjectedGreetingService.
        PropertyInjectedController propertyInjectedController = (PropertyInjectedController)ctx.getBean("propertyInjectedController");
        System.out.println(propertyInjectedController.getGreeting());

        System.out.println("-----------Setter Based DI");
        // we had to add the @Controller annotation to the SetterInjectedController and @AutoWired to the setGreetingService() setter.
        // not really recommended to use this type of DI
        // We use the @Qualifier("setterInjectedGreetingService") to force it to use the SetterInjectedGreetingService.
        SetterInjectedController setterInjectedController = (SetterInjectedController)ctx.getBean("setterInjectedController");
        System.out.println(setterInjectedController.getGreeting());

        System.out.println("-----------Constructor Based DI");
        // Just had to add @Controller to the ConstructorInjectedController but @autowired is optional on the constructor.  Spring handles it.
        // this is the recommended way of using di.
        // We use the @Qualifier("constructorInjectedGreetingService") to force it to use the ConstructorInjectedGreetingService.
        ConstructorInjectedController constructorInjectedController = (ConstructorInjectedController)ctx.getBean("constructorInjectedController");
        System.out.println(constructorInjectedController.getGreeting());
    }
}
