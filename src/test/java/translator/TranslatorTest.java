package translator;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TranslatorTest {
    @Test
    public void mapToDictionary() throws FileNotFoundException {
        String pathDict = "src/test/resources/dictionary.txt";
        Translator translator = new Translator();
        Map<String, String> map = translator.dictionaryToMap(pathDict);
        assertEquals(map.get("футбол"), ("football"));
    }

    @Test
    public void translationTest() throws FileNotFoundException {
        String pathText = "src/test/resources/text.txt";
        String pathDict = "src/test/resources/dictionary.txt";
        Translator translator = new Translator();
        translator.dictionaryToMap(pathDict);
        String result = translator.translateFile(pathText);
        String exp = "i very like play в football на sports ground";
        assertEquals(exp, result);
    }
}