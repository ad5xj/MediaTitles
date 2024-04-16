/*
 * (C)Copyright 2023 ken
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/** @file Dao.java */
package org.ad5xj.dao;
/**
 * @ingroup MARIADB
 * @brief Interface definition of basic I/O functions in Data Access Object (DAO) model
 * @details
 * This interface is inherited by each implementation of a Data Object.
 * @author ken
 * @copyright
 * &copy;Copyright 2023 ken AD5XJ@qso.com see the copyright notice in the help files.
 * \license
 * This product is released under the GNU Public license found in the
 * help documentation.
 * @param <T> T is Data Object
 */
public interface Dao<T>
{
    // CRUD Methods //
    public boolean add(T t);
    public boolean update(T t);
    public boolean destroy(int i_id);
    // END CRUD Methods //
}
