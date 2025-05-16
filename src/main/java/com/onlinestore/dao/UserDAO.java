package com.onlinestore.dao;

import java.util.List;
import com.onlinestore.entities.User;
import com.onlinestore.exeptions.DaoException;


public interface UserDAO {

    /**
     * Создает нового пользователя.
     * @param user Объект пользователя для сохранения.
     * @return Созданный пользователь с присвоенным ID.
     * @throws DaoException Ошибка доступа к данным.
     */
    User create(User user) throws DaoException;

    /**
     * Возвращает пользователя по ID.
     * @param id Уникальный идентификатор пользователя.
     * @return Пользователь, обернутый в Optional, если найден.
     * @throws DaoException Ошибка доступа к данным.
     */
    User findById(int id) throws DaoException;

    /**
     * Обновляет данные пользователя.
     * @param user Объект пользователя с обновленными данными.
     * @throws DaoException Ошибка доступа к данным.
     */
    void update(User user) throws DaoException;

    /**
     * Удаляет пользователя по ID.
     * @param id Уникальный идентификатор пользователя.
     * @throws DaoException Ошибка доступа к данных.
     */
    void delete(int id) throws DaoException;

    /**
     * Возвращает список всех пользователей.
     * @return Список пользователей.
     * @throws DaoException Ошибка доступа к данным.
     */
    List<User> findAll() throws DaoException;

    /**
     * Ищет пользователя по email.
     * @param email Email пользователя.
     * @return Пользователь, обернутый в Optional, если найден.
     * @throws DaoException Ошибка доступа к данным.
     */
    User findByEmail(String email) throws DaoException;

    /**
     * Проверяет существование пользователя с указанным email.
     * @param email Email для проверки.
     * @return true, если пользователь существует.
     * @throws DaoException Ошибка доступа к данным.
     */
    boolean existsByEmail(String email) throws DaoException;
}