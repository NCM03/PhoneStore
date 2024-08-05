package fa.training.phonestore.config;

import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new Formatter<Date>() {
            @Override
            public Date parse(String text, Locale locale) throws ParseException, ParseException {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                return format.parse(text);
            }

            @Override
            public String print(Date object, Locale locale) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                return format.format(object);
            }
        });
    }
}
