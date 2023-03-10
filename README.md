# Test-Proj

A Spring Boot app with Java 17, Maven, H2 and JdbcTemplate.

Task:
Функциональные требования:
- **(DONE)** Должна быть возможность "регистрации" (можно упростить процесс до простого сохранения логина в бд без указания/проверки пароля)
- **(DONE)** Должна быть возможность залогиниться под зарегистрированным пользователем (можно упростить процесс до простого ввода логина при входе), чтобы была возможность работы в системе нескольких пользователей.
- Должна быть возможность добавить вопросы и ответы на них.
- **(DONE)** Вопросы могут быть 2х типов: со свободным вводом ответа, с выбором ответа из 4х вариантов. Количество вопросов - 5 штук.
- **(DONE)** Должна быть возможность добавить от имени текущего пользователя ответ на вопрос с сохранением ответа в бд.
- **(DONE)** Система должна проверить введенный пользователем ответ на вопрос и сверить его с правильным ответов на этот вопрос. Результат проверки сохраняется в бд для конкретного пользователя.
- **(DONE)** Должна быть возможность пользователю посмотреть только свои ответы
- **(DONE)** (Опциональное требование) Должна быть возможность посмотреть статистику прохождения "тестирования". В статистике должно отображаться:
- **(DONE)** сколько всего пользователей зарегистрировано в системе
- **(DONE)**  сколько пользователей прошли тестирование
- **(DONE)** сколько пользователей ответили на все вопросы тестирования
- **(DONE)**  сколько пользователей ответили на все вопросы тестирования правильно.
- **(DONE)** (Опциональное требование) Должна быть возможность посмотреть статистику по текущему пользователю:
- **(DONE)** процент прохождения тестирования текущего пользователя (сколько правильных ответов он дал)
- сколько процентов людей справилось с тестированием хуже текущего пользователя
- сколько процентов людей справилось с тестированием лучше текущего пользователя.
Не функциональные требования:
- **(DONE)** Код оформить на гитхабе и прислать ссылку на репозиторий.
- **(DONE)** В поставке должна быть понятная инструкция по запуску сервиса
- **(DONE)** (Опциональное требование)  Поставка и запуск сервиса должны осуществляться средствами docker
- **(DONE)** Используемые технологии: Java 8+, Spring 5+, SpringBoot 2+, Maven 3+.  REST архитектура.
- **(DONE)** Все сервисы в программе должны решать строго определённую задачу (см SOLID).
- **(DONE)** Все сервисы должны быть определены в конфигурации Spring и инжектится по месту использования.
- **(DONE)**  База данных может быть inmemory.
- **(DONE)** Для работы с БД желательно использовать Spring JDBC template или Spring Data JDBC или просто JDBC. ( не надо использовать JPA и Hibernate ).
- **(DONE, покрыто частично)** (Опциональное требование) сервис, по возможности, покрыть Unit-тестами.

## How to run

To run the app locally:

1. Clone the repository
   git clone git@github.com:NicolaBilinats/test-proj-back.git
2. To run local:
   mvn spring-boot:run
3. To run with docker:
   docker-compose up -d


The app will be running on `http://localhost:8080/`

