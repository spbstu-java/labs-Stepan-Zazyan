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
                for (Map.Entry<String, String> entry : dictionary.entrySet()) {
                    if ((key.contains(entry.getKey()) || entry.getKey().contains(key))
                            && key.length() > entry.getKey().length()) {
                        key = entry.getKey();
                    }
                }
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
        String translated = String.join(" ", list).toLowerCase();
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            translated = translated.replace(entry.getKey(), entry.getValue());
        }
        return translated;
    }
}
