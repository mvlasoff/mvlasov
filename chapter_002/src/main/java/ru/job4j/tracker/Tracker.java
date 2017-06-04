package ru.job4j.tracker;

import java.util.Random;
import static java.lang.Math.abs;

/**.
 * Class Tracker puts all tasks in array
 * @author Mikhail Vlasov
 * @since 5.15.2017
 * @version 1
 */
public class Tracker {
    /**.
     * General array for tasks
     */
    private Item[] items = new Item[100];
    /**.
     * Position for new Item
     */
    private int position = 0;
    /**.
     * Random object
     */
    private static final Random RAND = new Random();

    /**.
     * Generates Id when new Item added
     * @return String id
     */
    private String generateId() {
        return String.valueOf(abs(System.currentTimeMillis() + RAND.nextLong()));
    }

    /**.
     * Adds item in array
     * @param item to add
     * @return Item
     */
    public Item add(Item item) {
        item.setId(generateId());
        this.items[position++] = item;
        return item;
    }

    /**.
     * Updates item in array
     * @param item which needs to be updated
     */
    public void update(Item item) {
        String id = item.getId();
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].getId().equals(id)) {
                items[i] = item;
                break;
            }
        }
    }

    /**.
     * Delets item from array
     * @param item which need to delete
     */
    public void delete(Item item) {
        String id = item.getId();
        Item[] temp = new Item[items.length];
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].getId().equals(id)) {
                /**.
                 * If an item found by id then moves array to the left in one position replacing the item
                 */
                System.arraycopy(items, i + 1, items, i, temp.length - 1 - i);
                items[items.length - 1] = null;
                break;
            }
        }
    }

    /**.
     * Finds all not null items
     * @return Item[] array
     */
    public Item[] findAll() {
        int index = 0;
        Item[] result = new Item[items.length];

        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                result[index++] = items[i];
            }
        }

        if (index == 0) {
            return null;
        }

        Item[] finalResult = new Item[index];
        System.arraycopy(result, 0, finalResult, 0, finalResult.length);
        return finalResult;
    }

    /**.
     * Finds all not null items by name
     * @param key to find by name
     * @return Item[] array
     */
    public Item[] findByName(String key) {
        Item[] result = new Item[items.length];
        int index = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].getName().equals(key)) {
                    result[index++] = items[i];
            }
        }

        if (index == 0) {
            return null;
        }

        Item[] finalResult = new Item[index];
        System.arraycopy(result, 0, finalResult, 0, finalResult.length);
        return finalResult;
    }

    /**.
     * Finds an item by ID
     * @param id to find by ID
     * @return Item item
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }
}
