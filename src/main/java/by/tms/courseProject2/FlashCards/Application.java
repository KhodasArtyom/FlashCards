package by.tms.courseProject2.FlashCards;

import by.tms.courseProject2.FlashCards.models.FlashCardsThemes;
import by.tms.courseProject2.FlashCards.repository.FlashCardsJDBCRepository;
import by.tms.courseProject2.FlashCards.repository.FlashCardsRepository;
import by.tms.courseProject2.FlashCards.repository.FlashCardsThemesJDBCRepository;
import by.tms.courseProject2.FlashCards.repository.FlashCardsThemesRepository;
import org.postgresql.ds.PGSimpleDataSource;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        dataSource.setURL("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUser("postgres");
        dataSource.setPassword("1279");
        FlashCardsThemesRepository flashCardsThemesRepository = new FlashCardsThemesJDBCRepository(dataSource);
        FlashCardsRepository flashCardsRepository = new FlashCardsJDBCRepository(dataSource);


        flashCardsThemesRepository.remove(2);
        flashCardsThemesRepository.save("Cars");
        flashCardsThemesRepository.save("Bikes");
        List<FlashCardsThemes> list  = flashCardsThemesRepository.getAllThemes();
        System.out.println(list);
        flashCardsRepository.save(1,"engine","Двигатель",false);
        flashCardsRepository.remove(1);





    }
}
