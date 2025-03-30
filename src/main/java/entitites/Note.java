package entitites;

public enum Note {
    TEN(10),
    TWENTY(20),
    FIFTY(50),
    HUNDRED(100);

    public final int value;

    Note(int value) {
        this.value = value;
    }
}
