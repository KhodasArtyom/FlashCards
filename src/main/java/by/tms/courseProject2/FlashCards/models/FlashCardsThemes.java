package by.tms.courseProject2.FlashCards.models;

public class FlashCardsThemes {

    private final long id;
    private final String title;

    private final long totalNumberOfCards;
    private final long totalIsLearnedCards;

    public long getTotalNumberOfCards() {
        return totalNumberOfCards;
    }

    public long getTotalIsLearnedCards() {
        return totalIsLearnedCards;
    }

    public FlashCardsThemes(long id, String title, long totalNumberOfCards, long totalIsLearnedCards) {
        this.id = id;
        this.title = title;
        this.totalNumberOfCards = totalNumberOfCards;
        this.totalIsLearnedCards = totalIsLearnedCards;


    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "FlashCardsThemes{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", totalNumberOfCards=" + totalNumberOfCards +
                ", totalIsLearnedCards=" + totalIsLearnedCards +
                '}';
    }
}
