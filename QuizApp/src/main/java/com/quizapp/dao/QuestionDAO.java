package com.quizapp.dao;

import com.quizapp.model.Question;
import com.quizapp.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class QuestionDAO {

    // Method to add a question to the database
    public boolean addQuestion(Question question) {
        Transaction transaction = null;
        boolean success = false;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(question);
            transaction.commit();
            success = true;
        } catch (Exception e) {
            if (transaction != null && transaction.getStatus().canRollback()) { 
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return success;
    }

    // Method to fetch all questions from the database
    public List<Question> getAllQuestions() {
        List<Question> questions = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Question> query = session.createQuery("FROM Question", Question.class);
            questions = query.list();  // Use .list() instead of getResultList()
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questions;
    }

    // Method to delete a question by ID
    public boolean deleteQuestion(int id) {
        Transaction transaction = null;
        boolean success = false;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Question question = session.get(Question.class, id);
            if (question != null) {
                session.delete(question);
                transaction.commit();
                success = true;
            }
        } catch (Exception e) {
            if (transaction != null && transaction.getStatus().canRollback()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return success;
    }

    // Method to update a question
    public boolean updateQuestion(Question updatedQuestion) {
        Transaction transaction = null;
        boolean success = false;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(updatedQuestion);
            transaction.commit();
            success = true;
        } catch (Exception e) {
            if (transaction != null && transaction.getStatus().canRollback()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return success;
    }

    // Method to fetch a single question by ID
    public Question getQuestionById(int id) {
        Question question = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            question = session.get(Question.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return question;
    }
}
