package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SelenidTestV1 {
    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }


    @BeforeEach
    void openBrowser() {
        open("http://localhost:9999");
        $("[data-test-id=\"date\"] [placeholder=\"Дата встречи\"]").sendKeys(Keys.CONTROL, "a" + Keys.DELETE);
    }

    @Test
    void shouldValidValues() {
        $("[data-test-id=\"city\"] [placeholder=\"Город\"]").val("Ростов-на-Дону");
        $("[data-test-id=\"date\"] [placeholder=\"Дата встречи\"]").val(generateDate(3));
        $("[data-test-id=\"name\"] [name=\"name\"]").val("Анна Лазарева");
        $("[data-test-id=\"phone\"] [name=\"phone\"]").val("+12345678901");
        $("[data-test-id=\"agreement\"] [class=\"checkbox__box\"]").click();
        $(".button").click();
        $("[data-test-id=\"notification\"] [class=\"notification__title\"]").shouldBe(text("Успешно!"), Duration.ofSeconds(15));
        $("[data-test-id=\"notification\"] [class=\"notification__content\"]").shouldBe(text("Встреча успешно забронирована на " + generateDate(3)));
    }
}