package by.tms.courseProject2.FlashCards.service;


import by.tms.courseProject2.FlashCards.models.FlashCards;

import java.util.List;
import java.util.Optional;

public interface FlashCardService  {

    void addNewCard(long flashCardId,String question,String answer);

    FlashCards deleteCard(long flashCardId);

    FlashCards setStatusOfKnowledge(long flashCardId, boolean isKnown);


    List<FlashCards> findCardsByThemeId(long flashCardId);

    Optional<FlashCards> getNextFlashCard(long themeId, long previousFlashCardId);

    FlashCards getTheFirstFlashCard(long themeId);

    void markIsDone(long flashCardId);






}
