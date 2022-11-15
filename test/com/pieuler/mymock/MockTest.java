package com.pieuler.mymock;

import com.pieuler.domain.Book;
import com.pieuler.domain.BookService;
import com.pieuler.domain.Entity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.pieuler.mymock.Mock.mock;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Implement Mock F/W
 * step 1. proxy
 * step 2. save the return value into map
 * step 3. use a global value to distinguish stubbing and method call
 * step 4. TODO: use a builder object to force return type to method's return type
 * step 5. TODO: matcher, class proxy, throws
 */
class MockTest {
    @Test
    void test() {
        Mock mock = new Mock();
        assertNotNull(mock);
    }

    @Test
    void shouldCreateMockObject() {
        BookService service = mock(BookService.class);
        assertInstanceOf(BookService.class, service);
        assertEquals(null, service.get(0));
    }

    @Test
    void shouldCreateMockObjectAsProxy() {
        BookService service = mock(BookService.class);
        assertInstanceOf(BookService.class, service);
    }

    @Test
    void shouldStubWithExpectedReturnValue() {
        BookService service = mock(BookService.class);
        Mock.stub(service.get(0), Book.of("stubbed proxy"));
        assertInstanceOf(BookService.class, service);
        assertEquals("stubbed proxy", service.get(0).getTitle());
    }

    @Test
    void shouldTwoStubsOfTheSameType() {
        BookService service = mock(BookService.class);
        Mock.stub(service.get(0), Book.of("stubbed proxy"));
        BookService service2 = mock(BookService.class);
        Mock.stub(service2.get(0), Book.of("stubbed proxy2"));

        assertEquals("stubbed proxy", service.get(0).getTitle());
        assertEquals("stubbed proxy2", service2.get(0).getTitle());
    }

    @Test
    void shouldReturnOtherValuesByMethods() {
        BookService service = mock(BookService.class);
        Mock.<Entity>stub(service.get(0), Book.of("hi"));
        Mock.<Entity>stub(service.get(1), Book.of("hi value"));
        Mock.<List>stub(service.list(), List.of(Book.of("hi value 2")));

        assertEquals("hi", service.get(0).getTitle());
        assertEquals("hi value", service.get(1).getTitle());
        assertEquals("hi value 2", service.list().get(0).getTitle());
    }

    @Test
    void shouldRedefineStub() {
        BookService service = mock(BookService.class);
        Mock.stub(service.get(0), Book.of(""));
        Mock.stub(service.get(0), Book.of("stubbed proxy"));

        assertEquals("stubbed proxy", service.get(0).getTitle());
    }
}