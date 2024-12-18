# Opportu.Net

### Description
Opportu.Net - это сервис, который предостовляет возможность студентам найти стажировку или работу, а работодателям набрать способных работников.

✅ - сделано
❌ - не сделано

### Ideas
* Интегрировать Redis для вакансий, чтобы самые просматриваемые вакансии кэшировались
* Лента новостей из IT
* Возможность компаниям писать статьи
* Возможность компаниям создавать свои контесты/хакатоны/идеятоны для отбора новых работников и нахождения новых идей
* Возможность компаниям создавать свои курсы квалификации
* Поиск статьей/новостей с помощью ElasticSearch
* Чат между студентом и компанией
* Создание умного помощника для подбадривания студентов
* Одноразовые вакансии(в формате фриланса)
* Интеграция с Linkedin/Github/LeetCode
* Сообщество для обсуждения/общие чаты/вопросы
* Тесты для подтверждения квалификации

### Endpoints
#### Auth
* ✅ POST: /auth/sign-in - авторизация с помощью email и password
* ✅ POST: /auth/sign-up/student - регистрация студента
* ✅ POST: /auth/sign-up/company - регистрация компании

#### Student
* ✅ GET: students/{studentId} - профиль студента
* ✅ GET: students/ - все студенты
* ✅ GET: students/{studentId}/details - детали профиля пользователя
* ✅ PATCH: students/{studentId}/details - редактирования профиля
* ✅ GET: students/{studentId}/cv - получить CV студента
* ❌ POST: students/{studentId}/cv - загрузить CV студента
* ✅ DELETE: students/{studentId} - удаление аккаунта студента
* ✅ GET: students/{studentId}/respondedVacancies - список откликнувшихся вакансий

#### Company
* ✅ GET: companies/{companyId} - профиль компании
* ✅ GET: companies/ - все компании
* ✅ GET: companies/{companyId}/detail - детали профиля компании
* ✅ PATCH: companies/{companyId}/edit - редактирования профиля
* ✅ DELETE: companies/{companyId} - удаление аккаунта компании
* ✅ GET: companies/{companyId}/vacancies - список вакансий компании

#### Vacancies
* ✅ GET: /vacancies/{vacancyId} - страница вакансии
* ✅ GET: /vacancies - все вакансии
* ✅ POST: /vacancies - создать вакансию
* ✅ PATCH: /vacancies/{vacancyId} - редактировать вакансию
* ✅ DELETE: /vacancies/{vacancyId} - удаление вакансии
* ✅ GET: /vacancies/{vacancyId}/responders - список откликнувшихся студентов
* ✅ POST: /vacancies/{vacancyId}/respond - откликнуться на вакансию
* ✅ POST: /vacancies/{vacancyId}/cancelRespond - отменить отклик на вакансию


