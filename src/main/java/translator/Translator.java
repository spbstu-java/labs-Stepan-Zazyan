package translator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Translator {
    private final Map<String, String> dictionary = new HashMap<>();

    public Map<String, String> dictionaryToMap(String path) {
        if (path == null || path.isBlank()) {
            throw new RuntimeException();
        }
        File file = new File(path);
        if (!file.exists() || !file.isFile() || !file.canRead()) {
            throw new RuntimeException();
        }
        try (Scanner scannerDictionary = new Scanner(file)) {
            while (scannerDictionary.hasNextLine()) {
                String[] line = scannerDictionary.nextLine().split("\\|");
                String key = line[0].trim().toLowerCase();
                String value = line[1].trim().toLowerCase();
                if (!dictionary.containsKey(key)) {
                    dictionary.put(key, value);
                }
                if (dictionary.get(key).length() < value.length()) {
                    dictionary.replace(key, dictionary.get(key), value);
                }

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return dictionary;
    }

    public String translateFile(String path) {
        if (path == null || path.isBlank()) {
            throw new RuntimeException();
        }
        File file = new File(path);
        if (!file.exists() || !file.isFile() || !file.canRead()) {
            throw new RuntimeException();
        }
        List<String> list = new ArrayList<>();
        try (Scanner scannerText = new Scanner(file)) {
            while (scannerText.hasNext()) {
                list.add(scannerText.next().toLowerCase());
            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        String translated = String.join(" ", list).toLowerCase();

        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            translated = translated.replace(entry.getKey(), entry.getValue());
        }
        return translated;
    }
}
