package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class Constants {
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final Pattern NAME_PATTERN = Pattern.compile("[\\p{L}a-zA-Z ]+");
    public static final Pattern STUDENT_CODE_PATTERN = Pattern.compile("^[a-zA-Z0-9]{10}$");

    public static final double MIN_HEIGHT = 50.0;
    public static final double MAX_HEIGHT = 300.0;
    public static final double MIN_WEIGHT = 10.0;
    public static final double MAX_WEIGHT = 1000.0;
    public static final double MIN_GPA = 0.0;
    public static final double MAX_GPA = 10.0;

    public static final int MIN_YEAR = 1900;
    public static final int MAX_YEAR = LocalDate.now().getYear();
    public static final int MAX_LENGTH_NAME = 100;
    public static final int MAX_LENGTH_ADDRESS = 300;
    public static final int MAX_LENGTH_UNIVERSITY = 200;
}
