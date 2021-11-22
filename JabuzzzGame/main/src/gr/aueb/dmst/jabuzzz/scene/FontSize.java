package gr.aueb.dmst.jabuzzz.scene;

public enum FontSize {
    /**
     * MESSAGE_FONT_SIZE is the font size of message displayed on center.
     */
    MESSAGE_FONT_SIZE(18),
    /**
     * TEAM_DISPLAY_FONT_SIZE is the font size used in both team displays'
     * labels.
     */
    TEAM_DISPLAY_FONT_SIZE(18),
    /**
     * SCORE_DISPLAY_FONT_SIZE is the font size used in both score
     * displays' labels.
     */
    SCORE_DISPLAY_FONT_SIZE(20),
    /**
     * TIMER_FONT_SIZE is the font size used in Timer label.
     */
    TIMER_FONT_SIZE(25),
    /**
     * TIMER_TEXT_FONT_SIZE is the font size used in Timer text's
     * label.
     */
    TIMER_TEXT_FONT_SIZE(30);

    /**
     * FontSize is the how big the font is.
     */
    private double fontSize;
    FontSize(final double size) {
        this.fontSize = size;
    }

    /**
     * Gives back the font size of an enum value.
     * @return size of an enum value
     */
    public double getFontSize() {
        return fontSize;
    }
}
