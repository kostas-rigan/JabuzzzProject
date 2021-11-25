package utilities;

import gr.aueb.dmst.jabuzzz.scene.FontSize;

public class FontSizeEnumTest {

    public static void main(String[] args) {
        FontSize[] fontSizes = {FontSize.SCORE_DISPLAY_FONT_SIZE,
                FontSize.TEAM_DISPLAY_FONT_SIZE,
                FontSize.TIMER_FONT_SIZE,
                FontSize.TIMER_TEXT_FONT_SIZE};
        for (FontSize fontSize : fontSizes) {
            System.out.println(fontSize.getFontSize());
        }
    }

}
