import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.LoggerContext;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FooTest {

    @Test
    void barLogsMessageWithName() {
        var appender = appender();
        var logEventCaptor = ArgumentCaptor.forClass(LogEvent.class);
        var foo = new Foo();
        addAppender(appender);

        foo.bar("Matt");

        verify(appender, times(1)).append(logEventCaptor.capture());
        assertEquals("Hello, Matt", getMessage(logEventCaptor.getValue()));
    }

    private Appender appender() {
        var appender = mock(Appender.class);
        when(appender.isStarted()).thenReturn(true);
        when(appender.isStopped()).thenReturn(false);
        return appender;
    }

    private void addAppender(Appender appender) {
        var context = LoggerContext.getContext(false);
        var config = context.getConfiguration();

        config.getLoggers().values().forEach(loggerConfig -> {
            loggerConfig.addAppender(appender, null, null);
        });
        config.getRootLogger().addAppender(appender, null, null);
    }

    private String getMessage(LogEvent logEvent) {
        return logEvent.getMessage().getFormattedMessage();
    }
}
