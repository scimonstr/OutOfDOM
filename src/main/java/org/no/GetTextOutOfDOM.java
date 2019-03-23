package org.no;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class GetTextOutOfDOM {
    public static void main(String[] args) {
        final String TARGET_URL = "http://generadornombres.byethost6.com/hombre.php?i=1";
        final String START_TOKEN = "<!-- PHP Contenido -->";
        final String END_TOKEN = "<!-- // Fin PHP Contenido -->";
        final String NEW_LINE = System.getProperty("line.separator");

        final ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get(TARGET_URL);
        String pageSource = driver.getPageSource();
        String[] lines = pageSource.split(NEW_LINE);
        boolean collectLines = false;
        Collection<String> collectedLines = new ArrayList<>();
        for (String line : lines) {
            if (START_TOKEN.equals(line.trim())){
                collectLines = true;
                continue;
            } else if (END_TOKEN.equals(line.trim())) {
                break;
            }
            if (collectLines){
                collectedLines.add(line.trim());
            }
        }
        String result = String.join(NEW_LINE, collectedLines);
        System.out.println("result = " + result);
    }
}

