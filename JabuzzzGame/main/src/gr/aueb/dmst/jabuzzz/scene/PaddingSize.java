package gr.aueb.dmst.jabuzzz.scene;

public enum PaddingSize {
    /**
     * SMALL_HBOX_PSIZE is the size of padding between nodes that
     * belong in smaller HBox containers.
     */
    SMALL_HBOX_PSIZE(50),
    /**
     * SMALL_VBOX_PSIZE is the size of padding between nodes that
     * belong in smaller VBox containers.
     */
    SMALL_VBOX_PSIZE(10),
    /**
     * BIG_HBOX_PSIZE is the size of padding between nodes that
     * belong in bigger HBox containers.
     */
    BIG_HBOX_PSIZE(275),
    /**
     * BIG_VBOX_PSIZE is the size of padding between nodes that
     * belong in bigger VBox containers.
     */
    BIG_VBOX_PSIZE(150);

    /**
     * paddingSize is the how big the padding is.
     */
    private double paddingSize;
    PaddingSize(final double size) {
        this.paddingSize = size;
    }

    /**
     * Gives back the padding size of an enum value.
     * @return size of an enum value
     */
    public double getPaddingSize() {
        return paddingSize;
    }
}
