package by.tms.courseProject2.FlashCards.repository;

import by.tms.courseProject2.FlashCards.models.FlashCards;

import java.util.List;

public interface FlashCardsRepository {

void save(long flashCards_themes_id,String question,String answer,boolean isLearned);

void remove (long flashCardId);

boolean statusUpdateLearned(long flashCardId,boolean isLearned);


List<FlashCards> findAllFlashCards(long flashCards_themes_id, long nextAfterId);
}
