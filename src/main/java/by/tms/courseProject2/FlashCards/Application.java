package by.tms.courseProject2.FlashCards;

import by.tms.courseProject2.FlashCards.models.FlashCards;
import by.tms.courseProject2.FlashCards.models.FlashCardsThemes;
import by.tms.courseProject2.FlashCards.repository.FlashCardsJDBCRepository;
import by.tms.courseProject2.FlashCards.repository.FlashCardsRepository;
import by.tms.courseProject2.FlashCards.repository.FlashCardsThemesJDBCRepository;
import by.tms.courseProject2.FlashCards.repository.FlashCardsThemesRepository;
import org.postgresql.ds.PGSimpleDataSource;

import java.util.List;
import java.util.Optional;

public class Application {
    public static void main(String[] args) {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        dataSource.setURL(System.getenv("variableUrl"));
        dataSource.setUser(System.getenv("variableUser"));
        dataSource.setPassword(System.getenv("variablePassword"));
        FlashCardsThemesRepository flashCardsThemesRepository = new FlashCardsThemesJDBCRepository(dataSource);
        FlashCardsRepository flashCardsRepository = new FlashCardsJDBCRepository(dataSource);


        flashCardsThemesRepository.save("COLORS");
        flashCardsThemesRepository.save("NUMBERS");

        flashCardsThemesRepository.remove(2);

        flashCardsRepository.save(1, "Black", "Чёрный", false);
        flashCardsRepository.save(1, "White", "Белый", false);
        flashCardsRepository.save(1, "Orange", "Оранжевый", false);
        flashCardsRepository.save(1, "Green", "Зелёный", false);
        flashCardsRepository.save(1, "Жёлтый", "Жёлтый", false);

        List<FlashCards> allByThemeId = flashCardsRepository.findAllByThemeId(1);
        System.out.println(allByThemeId);

        System.out.println("=========================");

        Optional<FlashCards> allFlashCardsByIdAndOffset = flashCardsRepository.findAllFlashCardsByIdAndOffset(1, 0);
        System.out.println(allFlashCardsByIdAndOffset);

        flashCardsRepository.statusUpdateLearned(2,true);
        flashCardsRepository.statusUpdateLearned(3,true);



    }
}
