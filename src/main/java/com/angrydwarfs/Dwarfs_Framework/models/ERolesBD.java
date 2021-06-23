package com.angrydwarfs.Dwarfs_Framework.models;
/**
 * Перечень возможных ролей пользователя.
 * Записывается в БД в таблицу с имененм rolesBD.
 * @see RoleBD (таблица ролей).
 */
public enum ERolesBD {
    ROLE_USER,
    ROLE_ADMIN,
    ROLE_MODERATOR
}
