package com.kimtaehyun84.webservice.domain.posts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(CustomParameterResolver.class)
class PersonTest extends Person {

    public PersonTest(String name, int age) {
        super(name, age);
    }

    @Test
    @DisplayName("Person's nameDecorate method's test")
    void testNameDecorate() {
        Person p = new Person("kukaro",26);
        assertEquals("kukarogood", p.nameDecorate("good"),"fail");
    }

    @Test
    void testIsMatchAge() {
    }
}
class CustomParameterResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
                                     ExtensionContext extensionContext) throws ParameterResolutionException {
        return (parameterContext.getParameter().getType() == Person.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext,
                                   ExtensionContext extensionContext) throws ParameterResolutionException {
        return new Person();
    }
}