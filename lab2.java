/*
 * Номер залікової книжки: 19
 * C3 (остача від ділення на 3): 1
 * C17 (остача від ділення на 17): 2
 * Тип текстових змінних: String
 * Дія з текстом: Знайти таке слово в першому реченні заданого тексту, якого немає в жодному з наступних.
 */
public class lab2 {
    public static void main(String[] args) {
        try {
            // Заданий текст
            String text = "Це перше речення. Це друге речення. А це третє речення.";

            // Розділення тексту на речення
            String[] sentences = text.split("\\.");

            // Перевірка, чи є принаймні одне речення
            if (sentences.length == 0) {
                throw new IllegalArgumentException("Текст не мiстить жодного речення.");
            }

            // Отримання першого речення та інших речень
            String firstSentence = sentences[0].trim();
            String[] subsequentWords = new String[100]; // Массив для слів з наступних речень
            int index = 0;

            // Додавання слів з інших речень до масиву
            for (int i = 1; i < sentences.length; i++) {
                String[] words = sentences[i].trim().split("\\s+");
                for (String word : words) {
                    boolean exists = false;
                    // Перевірка, чи слово вже додано
                    for (int j = 0; j < index; j++) {
                        if (subsequentWords[j].equalsIgnoreCase(word)) {
                            exists = true;
                            break;
                        }
                    }
                    if (!exists) {
                        subsequentWords[index++] = word.toLowerCase(); // Додаємо слово до масиву
                    }
                }
            }

            // Пошук унікальних слів у першому реченні
            String[] firstSentenceWords = firstSentence.trim().split("\\s+");
            String uniqueWord = null;

            for (String word : firstSentenceWords) {
                boolean isUnique = true;
                // Перевірка наявності слова в масиві subsequentWords
                for (int j = 0; j < index; j++) {
                    if (subsequentWords[j].equalsIgnoreCase(word)) {
                        isUnique = false;
                        break;
                    }
                }
                if (isUnique) {
                    uniqueWord = word;
                    break; // Зупиняємося після знаходження першого унікального слова
                }
            }

            // Вивід результатів
            if (uniqueWord != null) {
                System.out.println("Перше унiкальне слово в першому реченнi: " + uniqueWord);
            } else {
                System.out.println("В першому реченнi немає унiкальних слiв.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Невiдома помилка: " + e.getMessage());
        }
    }
}
