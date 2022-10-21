package translator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Translator {
    private final Map<String, String> dictionary = new HashMap<>();

    public Map<String, String> dictionaryToMap(String path) throws FileNotFoundException {
        if (path == null || path.isBlank()) {
            throw new RuntimeException("Некорректный путь к словарю");
        }
        File file = new File(path);
        if (!file.exists() || !file.isFile() || !file.canRead()) {
            throw new RuntimeException("Проблема с открытием файла словаря");
        }
        try (Scanner scannerDictionary = new Scanner(file)) {
            while (scannerDictionary.hasNextLine()) {
                String[] line = scannerDictionary.nextLine().split("\\|");
                String key = line[0].trim().toLowerCase();
                String value = line[1].trim().toLowerCase();
                dictionary.put(key, value);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Файл не найден во время создания мапы");
        }
        return dictionary;
    }

    public String translateFile(String path) throws FileNotFoundException {
        if (path == null || path.isBlank()) {
            throw new RuntimeException("Некорректный путь к тексту для перевода");
        }
        File file = new File(path);
        if (!file.exists() || !file.isFile() || !file.canRead()) {
            throw new RuntimeException("Проблема с открытием текстового файла");
        }
        List<String> list = new ArrayList<>();
        try (Scanner scannerText = new Scanner(file)) {
            while (scannerText.hasNext()) {
                list.add(scannerText.next().toLowerCase());
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("файл не найден во время переброса в строку");
        }
        String translated = "";
        for (int i = 0; i < list.size(); i++) {
            if (i != 0 && dictionary.containsKey(list.get(i - 1) + " " + list.get(i))) {
                translated += dictionary.get(list.get(i - 1) + " " + list.get(i)) + " ";
                i++;
                continue;
            }
            if (i != list.size() - 1 && dictionary.containsKey(list.get(i) + " " + list.get(i + 1))) {
                translated += dictionary.get(list.get(i) + " " + list.get(i + 1)) + " ";
                i++;
                continue;
            }
            if (dictionary.containsKey(list.get(i))) {
                translated += dictionary.get(list.get(i)) + " ";
            } else {
                translated += list.get(i) + " ";
            }
        }
        return translated.trim();
    }
}
