package by.tms.courseProject2.FlashCards.service;


import by.tms.courseProject2.FlashCards.exception.ServiceException;
import by.tms.courseProject2.FlashCards.models.FlashCards;
import by.tms.courseProject2.FlashCards.repository.FlashCardsRepository;
import by.tms.courseProject2.FlashCards.repository.FlashCardsThemesRepository;

import java.util.List;
import java.util.Optional;

public class FlashCardServiceImpl implements FlashCardService {

    private final FlashCardsRepository flashCardsRepository;
    private final FlashCardsThemesRepository flashCardsThemesRepository;
    private static final boolean DEFAULT_IS_LEARNED = false;

    public FlashCardServiceImpl(FlashCardsRepository flashCardsRepository, FlashCardsThemesRepository flashCardsThemesRepository) {
        this.flashCardsRepository = flashCardsRepository;
        this.flashCardsThemesRepository = flashCardsThemesRepository;
    }

    @Override
    public void addNewCard(long flashCardId, String question, String answer) {
        if (question.isEmpty() || answer.isEmpty()) {
            throw new ServiceException();
        } else if (!flashCardsThemesRepository.isExist(flashCardId)) {
            throw new ServiceException();
        } else {
            flashCardsRepository.save(flashCardId, question, answer);
        }
    }

    @Override
    public FlashCards deleteCard(long flashCardId) {
        {
            FlashCards flashCard = flashCardsRepository.findFlashCardById(flashCardId)
                    .orElseThrow(ServiceException::new);
            flashCardsRepository.remove(flashCardId);
            return flashCard;

        }

    }

    @Override
    public FlashCards setStatusOfKnowledge(long flashCardId, boolean isKnown) {
        boolean isExist = flashCardsRepository.statusUpdateLearned(flashCardId,isKnown);
        if(!isExist) {
            throw new ServiceException();
        }
        return  flashCardsRepository.findFlashCardById(flashCardId).orElseThrow();
    }


    @Override
    public List<FlashCards> findCardsByThemeId(long flashCardId) {
        return flashCardsRepository.findAllCardsByThemeId(flashCardId);
    }

    @Override
    public Optional<FlashCards> getNextFlashCard(long themeId, long previousCardId) {
        FlashCards card;
        if (flashCardsRepository.isExist(previousCardId)) {
            card = flashCardsRepository.getFlashCardById(previousCardId);
        } else {
            throw new ServiceException();
        }
        return flashCardsRepository.getOneFlashCardNotLearned(themeId, previousCardId);
    }

    @Override
    public FlashCards getTheFirstFlashCard(long themeId) {
        return flashCardsRepository.getOneFlashCardNotLearned(themeId, 0).orElseThrow(ServiceException::new);

    }

    @Override
    public void markIsDone(long flashCardId) {
        boolean isExist = flashCardsRepository.statusUpdateLearned(flashCardId, true);
        if (!isExist) throw new ServiceException();
    }

    private void checkIfExist(long themeId) {
        if (!flashCardsThemesRepository.isExist(themeId)) {
            throw new ServiceException();
        }
    }
}
