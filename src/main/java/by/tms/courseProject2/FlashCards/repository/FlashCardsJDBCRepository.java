package by.tms.courseProject2.FlashCards.repository;

import by.tms.courseProject2.FlashCards.exception.RepositoryException;
import by.tms.courseProject2.FlashCards.models.FlashCards;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class FlashCardsJDBCRepository implements FlashCardsRepository {

    private final DataSource db;

    public FlashCardsJDBCRepository(DataSource db) {
        this.db = db;
    }

    @Override
    public void save(long flashCards_themes_id, String question, String answer, boolean isLearned) {
        String sql = """
                INSERT INTO flashcards(flashCards_themes_id, question, answer, status_knowledge)
                VALUES (?,?,?,?)             
                """;
        try (Connection connection = db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setLong(1, flashCards_themes_id);
            statement.setString(2,question);
            statement.setString(3,answer);
            statement.setBoolean(4,isLearned);

        } catch (SQLException e) {
            throw new RepositoryException(e);
        }


    }

    @Override
    public boolean remove(long flashCardId) {
        return false;
    }

    @Override
    public boolean statusUpdateLearned(long flashCardId, boolean isLearned) {
        return false;
    }

    @Override
    public List<FlashCards> findAllFlashCards(long flashCards_themes_id, long nextAfterId) {
        return null;
    }
}
