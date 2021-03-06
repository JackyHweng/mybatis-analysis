/**
 *    Copyright 2009-2016 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.executor.statement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.session.ResultHandler;

/**
 * @author Clinton Begin
 */

/**
 * statement 处理器
 * 1. StatementHandler 首先通过 ParameterHandler完成Sql语句的实参绑定
 * 2. 然后通过 java.sql.statement 对象执行Sql语句，并且获得结果集
 * 3. 最后通过 ResultSetHandler 完成结果集的映射，得到结果对象并返回
 *
 */
public interface StatementHandler {

  /**
   * 准备操作，可以理解成创建 Statement 对象
   * @param connection 连接对象
   * @param transactionTimeout 事务超时时间
   * @return
   * @throws SQLException
   */
  Statement prepare(Connection connection, Integer transactionTimeout)
      throws SQLException;

  /**
   * 设置 Statement 对象的参数
   * @param statement
   * @throws SQLException
   */
  void parameterize(Statement statement)
      throws SQLException;

  /**
   * 添加 Statement 对象的批量操作
   * @param statement
   * @throws SQLException
   */
  void batch(Statement statement)
      throws SQLException;

  /**
   * 添加 Statement 对象的更新操作
   * @param statement
   * @return
   * @throws SQLException
   */
  int update(Statement statement)
      throws SQLException;

  /**
   * 读操作
   * @param statement
   * @param resultHandler  ResultHandler 对象，处理结果
   * @param <E>
   * @return <E> 泛型
   * @throws SQLException
   */
  <E> List<E> query(Statement statement, ResultHandler resultHandler)
      throws SQLException;

  /**
   * 执行读操作，返回 Cursor 对象
   * @param statement
   * @param <E>
   * @return
   * @throws SQLException
   */
  <E> Cursor<E> queryCursor(Statement statement)
      throws SQLException;

  /**
   *  BoundSql 对象
   * @return
   */
  BoundSql getBoundSql();

  /**
   * ParameterHandler 对象
   * @return
   */
  ParameterHandler getParameterHandler();

}
