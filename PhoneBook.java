import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

class HeapSort {

    /**
     * @param heap     куча (массив элементов)
     * @param heapSize количество элементов в куче (длина массива)
     * @param index    индекс элемента-родителя
     */
    public static void heapify(Object[] heap, int heapSize, int index) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int largest = index;

        if (left <= (heapSize - 1)
                && ((Map.Entry<String, List<String>>) heap[left]).getValue().size() < ((Map.Entry<String, List<String>>) heap[largest]).getValue().size()) {
            largest = left;
        }
        if (right <= (heapSize - 1)
                && ((Map.Entry<String, List<String>>) heap[right]).getValue().size() < ((Map.Entry<String, List<String>>) heap[largest]).getValue().size()) {
            largest = right;
        }
        if (largest != index) {
            Map.Entry<String, List<String>> tmp = (Map.Entry<String, List<String>>) heap[largest];
            heap[largest] = heap[index];
            heap[index] = tmp;
            heapify(heap, heapSize, largest);
        }
    }

    /**
     * @param tree     куча (массив элементов) которую превращаем в дерево
     * @param heapSize количество элементов в куче (размер массива)
     */
    public static void buildTree(Object[] tree, int heapSize) {
        int lastParentIndex = heapSize / 2 - 1;
        for (int i = lastParentIndex; i >= 0; i--) {
            heapify(tree, heapSize, i);
        }
    }

    /**
     * @param sortArray массив элементов который нужно отсрортировать
     * @param heapSize  размер массива
     */
    public static void heapSort(Object[] sortArray, int heapSize) {
        // Введите свое решение ниже
        buildTree(sortArray, heapSize);
        for (int i = (heapSize - 1); i > 0; i--) {
            Map.Entry<String, List<String>> tmp = (Map.Entry<String, List<String>>) sortArray[0];
            sortArray[0] = sortArray[i];
            sortArray[i] = tmp;
            heapify(sortArray, i, 0);
        }
    }
}

public class PhoneBook {

    static Map<String, List<String>> phoneBook = new HashMap<>();

    public static void printPhoneBook() {
        Object[] phones = phoneBook.entrySet().toArray();
        HeapSort.heapSort(phones, phones.length);
        for (Object phone : phones) {
            Map.Entry<String, List<String>> item = (Map.Entry<String, List<String>>) phone;
            System.out.println(String.format("%1$-8s: %2$s", item.getKey(), item.getValue().toString()));
        }
    }

    public static void addPhone(String name, String phone) {
        if (phoneBook.get(name) == null) {
            List<String> phones = new ArrayList<>();
            phoneBook.put(name, phones);
        }
        phoneBook.get(name).add(phone);
    }

    public static void main(String[] args) {

        addPhone("Маша", "89998887766");
        addPhone("Маша", "89887772211");
        addPhone("Маша", "89776661122");
        addPhone("Миша", "89918817121");
        addPhone("Миша", "89918827231");
        addPhone("Юля", "89917735544");
        addPhone("Юля", "89917735646");
        addPhone("Алексей", "89925552243");
        addPhone("Егор", "89936674545");
        addPhone("Максим", "89937893311");
        addPhone("Настя", "89006783445");
        addPhone("Настя", "89005678997");

        System.out.println("Вывод телефонной книги:");
        printPhoneBook();
    }
}
