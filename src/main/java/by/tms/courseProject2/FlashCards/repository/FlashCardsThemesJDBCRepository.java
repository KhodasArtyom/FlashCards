package by.tms.courseProject2.FlashCards.repository;

import by.tms.courseProject2.FlashCards.exception.RepositoryException;
import by.tms.courseProject2.FlashCards.models.FlashCardsThemes;

import javax.sql.DataSource;
import java.sql.*;
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
                INSERT INTO flashcards_themes( set_name)
                VALUES (?)
                """;
        try (Connection connection = db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nameOfTheme);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public boolean remove(long id) {
        String sql = """
                DELETE
                FROM flashCards_themes
                WHERE id = ?
                """;
        try (Connection connection = db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return false;
    }

    @Override
    public List<FlashCardsThemes> getAllThemes() {
        String sql = """
                SELECT flashCards_themes.id                                              AS id,
                       flashCards_themes.set_name                                          AS name,
                       count(flashcards.id) FILTER ( WHERE flashcards.status_knowledge ) AS succsess,
                       count(flashcards.id)                                              AS global
                FROM flashCards_themes
                            LEFT JOIN flashcards ON flashCards_themes.id = flashcards.flashCards_themes_id
                GROUP BY flashCards_themes.id
                """;
        try (Connection connection = db.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            List<FlashCardsThemes> listResult = new ArrayList<>();
            while (resultSet.next()) {
                FlashCardsThemes flashCardsThemes = new FlashCardsThemes(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getLong("learnedFlashCards"),
                        resultSet.getLong("totalCountCards"));
                listResult.add(flashCardsThemes);
            }
            return listResult;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }

    }
}
