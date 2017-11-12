package capitaltwo;

public enum Ethnicity {
    WHITE("White"),
    LATINO("Hispanic or Latino"),
    BLACK("African American or Black"),
    NATIVE("Native American or American Indian"),
    ASIAN("Asian or Pacific Islander"),
    OTHER("Other");

    private String description;
    private Ethnicity(String description) { this.description = description; }
    public String getDescription() { return this.description; }
}
