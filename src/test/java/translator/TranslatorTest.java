package translator;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TranslatorTest {
    @Test
    public void mapToDictionary() {
        String pathDict = "src/test/resources/dictionary.txt";
        Translator translator = new Translator();
        Map<String, String> map = translator.dictionaryToMap(pathDict);
        assertEquals(map.get("люблю"), ("very very love"));
    }

    @Test
    public void translationTest() {
        String pathText = "src/test/resources/text.txt";
        String pathDict = "src/test/resources/dictionary.txt";
        Translator translator = new Translator();
        translator.dictionaryToMap(pathDict);
        String result = translator.translateFile(pathText);
        String expected = "i very very love play в football на sports ground";
        assertEquals(result, expected);
    }
}