package guru.springframework.sfgdi.services;

import org.springframework.stereotype.Service;

/*
 * {Aaron Scherger} created on 11/10/2020
 */
@Service
public class PropertyInjectedGreetingService implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Hello World - Property";
    }
}
