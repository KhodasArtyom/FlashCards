package by.tms.courseProject2.FlashCards.repository;

import by.tms.courseProject2.FlashCards.models.FlashCardsThemes;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlashCardsThemesJDBCRepository implements FlashCardsThemesRepository {
    private final DataSource db;

    public FlashCardsThemesJDBCRepository(DataSource db) {
        this.db = db;
    }


    @Override
    public void save(String nameOfTheme) {
        String sql = """
                INSERT INTO flashcards_themes(id, set_name)
                VALUES (?)
                """;
        try (Connection connection = db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,nameOfTheme);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean remove(long id) {
        return false;
    }

    @Override
    public List<FlashCardsThemes> getAllThemes() {
        return null;
    }
}
