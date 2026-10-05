static class Storage<T> {
    private T value;

    public Storage(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}

static class Pair<T1,T2>{
    public T1 first;
    public T2 second;
}

static void main() {
    Storage<String> storage = new Storage<>("Hello");
    storage.setValue("World");
    String value = storage.getValue();
    IO.println(value);

    ArrayList<String> strings = new ArrayList<>();
    strings.add("Hello");
    strings.add("World");
    ArrayList<Integer> integers = new ArrayList<>();
    Pair<String,LocalDateTime> pair = new Pair<>();


}
