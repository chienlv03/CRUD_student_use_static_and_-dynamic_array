package entity;

public enum AcademicAchievement {
    OUTSTANDING("Xuất sắc"),
    EXCELLENT("Giỏi"),
    GOOD("Khá"),
    AVERAGE("Trung bình"),
    WEEK("Yếu"),
    POOR("Kém");

    private final String value;

    AcademicAchievement(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
