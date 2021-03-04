package ru.lighthouse.mobile.main.unittest;


import org.assertj.core.api.Assertions;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(UnitTestsSample.LogExtension.class) // Расширение стандартного JunitEngine
@DisplayName("Unit test example")         // Аннотация позволяет задать удобное имя для отображения в отчетах
public class UnitTestsSample {

    /**
     * Метод помеченный аннотацией @BeforeAll вызывается один раз перед запуском тестов
     *
     * @param logger Этот параметр подставляется автоматически расширением {@link UnitTestsSample.LogExtension}
     */
    @BeforeAll
    static void init(Logger logger) {
        logger.info("init");
    }

    /**
     * Метод помеченный аннотацией @BeforeEach вызывается перед каждым тестом
     *
     * @param logger Этот параметр подставляется автоматически расширением {@link UnitTestsSample.LogExtension}
     */
    @BeforeEach
    void setup(Logger logger) {
        logger.info("setup");
    }

    /**
     * Метод помеченный аннотацией @AfterEach вызывается после каждого теста.
     *
     * @param logger Этот параметр подставляется автоматически расширением {@link UnitTestsSample.LogExtension}
     */
    @AfterEach
    void teardown(Logger logger) {
        logger.info("teardown");
    }

    /**
     * Метод помеченный аннотацией @BeforeAll вызывается один раз после выполнения всех тестов
     *
     * @param logger Этот параметр подставляется автоматически расширением {@link UnitTestsSample.LogExtension}
     */
    @AfterAll
    static void dispose(Logger logger) {
        logger.info("dispose");
    }

    /**
     * Тест с примерами утверждений и проверок описанных с помощью методов входящих в стандартную поставку JUnit5
     */
    @Test
    @DisplayName("JUnit5 native assertions")
    @Tag("junit")
    void junitAssertTest() {
        assertEquals("Junit5 is awesome!", "Junit5" + " is awesome!"); // Сравнение через метод .equals()

        assertThrows(SecurityException.class, () -> { // Проверка ожидаемого исключения
            throw new SecurityException("You are not allowed here");
        });

        assertTrue(() -> 1 != 2); // Проверка логического выражения

        assertAll("group check", // Группа проверок объединенных некой единой логикой
                  () -> assertTrue(true),
                  () -> assertFalse(false)
        );
    }

    /**
     * Тест с примерами утверждений и проверок описанных с помощью библиотеки <a href="http://hamcrest.org/JavaHamcrest/tutorial">Hamcrest</a>.
     * Библиотека отличается возможностью расширения функционала средставми кастомных матчеров а также человекочитаемыми сообщениями об ошибках при падении проверки, например:
     * <br/>
     * <code>Expected: every item is a value greater than <1>
     * but: an item <-2> was less than <1></code>
     * <br/>
     * <code>Expected: (an instance of ru.sbrf.bh.system.status.AbstractStatus and hasProperty("code", (is not <404L> and is a value greater than <500L>)) and hasProperty("message", (is "foo" or a string containing "any" ignoring case)))
     * but: hasProperty("message", (is "foo" or a string containing "any" ignoring case))  property 'message' was "Some message"</code>
     */
    @Test
    @DisplayName("Hamcrest assertions")
    @Tag("hamcrest")
    void hamcrestAssertTest() {
        assertThat("Hamcrest is awesome!", is(equalTo("Hamcrest" + " is awesome!"))); // Проверка с импользованием стандартного матчера equalTo

        assertThat("My test string", length(is(14))); // Можно писать свои специальные матчеры для удобства и сокращения кода

        assertThat(true, is(true)); // Логическая проверка

        assertThat(Arrays.asList(5, 2, 4), everyItem(greaterThan(1))); // Проверка соответствия критерию каждого элемента списка
    }

    /**
     * Тест с примерами утверждений и проверок описанных с помощью библиотеки <a href="https://assertj.github.io/doc/#overview-what-is-assertj">AssertJ</a>
     * Библиотека с fluent синтаксисом. Существуют много различных модулей которые расширяют его функционал, среди которых проверка таблиц баз данных
     */
    @Test
    @DisplayName("AssertJ assertions")
    @Tag("assertj")
    void assertjAssertTest() {
        Assertions.assertThat("AssertJ" + " is awesome!").isEqualTo("AssertJ is awesome!"); // Сравнение через .equals()
        Assertions.assertThat("AssertJ").isNotEqualTo("Hamcrest");// Негативное сравнение через .equals()

        Assertions.assertThat("AssertJ").startsWith("Ass") // Проверки строк
                  .endsWith("J")
                  .isEqualToIgnoringCase("aSsErTj");

        Assertions.assertThat(Arrays.asList("one", "two", "three")) // Проверка коллекций
                  .hasSize(3)
                  .contains("one", "three")
                  .doesNotContain("four");

        Assertions.assertThat(33)
                  .as("check %s value", Integer.class.getSimpleName()) // Метод as позволяет указать сообщение которое отобразиться в сообщении об ошибке
                  .isEqualTo(33);

        Assertions.assertThatThrownBy(() -> {
            throw new SecurityException("boom!");
        })
                  .isInstanceOf(RuntimeException.class)
                  .hasMessage("boom!"); // Проверка сообщения об ошибке
    }

    /**
     * Методы помеченные аннотацией RepeatedTest буду выполнены определенное количество раз.
     * Атрибут name позволяет указать удобное имя для каждой итерации
     */
    @RepeatedTest(value = 5, name = "{currentRepetition} time(s) of {totalRepetitions}")
    @DisplayName("Repeated test")
    void repeatedTest() {

    }

    /**
     * Тестовый метод параметризованный строковым значением.
     * Список параметров может быть перечислен явно, либо возвращен специализированным классом.
     * Более подробно в <a href="https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests">документации</a>
     *
     * @param argument Параметр
     */
    @ParameterizedTest
    @ValueSource(strings = {"Hello", "World"})
    @DisplayName("Test with parameter")
    void testWithParameter(String argument) {
        assertNotNull(argument);
    }

    /**
     * Вложенный класс тестов помечается аннотацией {@link Nested}. В результате чего, все тесты этого класса будет на один уровень глубже родительских
     */
    @Nested
    @DisplayName("Nested test class. Good stuff for grouping")
    class NestedTest {
        @Test
        @DisplayName("My test")
        void someTest() {
            assertTrue(true);
        }
    }

    /**
     * Пример реализации расширения который логирует имя тесты перед и после его выполнения, а также может подставить параметр {@link Logger} в аргументах тестового метода.
     */
    public static class LogExtension implements BeforeEachCallback, AfterEachCallback, ParameterResolver {
        static Logger log = LoggerFactory.getLogger(LogExtension.class);

        @Override
        public void beforeEach(ExtensionContext context) {
            log.info("beforeEach: {}", context.getDisplayName());
        }

        @Override
        public void afterEach(ExtensionContext context) {
            log.info("afterEach: {}", context.getDisplayName());
        }

        @Override
        public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
            return (parameterContext.getParameter().getType() == Logger.class) && extensionContext.getTestClass().isPresent();
        }

        @Override
        public Logger resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
            return LoggerFactory.getLogger(extensionContext.getTestClass().get());
        }
    }

    /**
     * Пример матчера который извлекает из строки ее длину и применяет к ней дальнейшую цепочку матчеров.
     */
    public static Matcher<String> length(Matcher<? super Integer> matcher) {
        return new FeatureMatcher<String, Integer>(matcher, "a String of length that", "length") {
            @Override
            protected Integer featureValueOf(String actual) {
                return actual.length();
            }
        };
    }
}