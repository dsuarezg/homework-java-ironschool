public enum CommandOptions {

    COURSES("COURSE"),
    STUDENTS("STUDENT"),
    TEACHERS("TEACHER"),
    PROFIT("PROFIT");

    private final String singular;

    CommandOptions(String singular) {
        this.singular = singular;
    }

    public String getSingular() {
        return singular;
    }

/*    public static CommandOptions fromString(String value) {
        for (CommandOptions type : CommandOptions.values()) {
            if (type.name().equalsIgnoreCase(value) || type.singular.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }*/
}
