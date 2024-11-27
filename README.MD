# Opportu.Net

### Description
Opportu.Net - это сервис, который предостовляет возможность студентам найти стажировку или работу, а работодателям набрать способных работников.

✅ - сделано
❌ - не сделано

### Endpoints
#### Auth (/auth)
* ✅ POST: /sign-in - авторизация с помощью email и password
* ✅ POST: /sign-up/student - регистрация студента
* ✅ POST: /sign-up/company - регистрация компании

#### Student
* ✅ GET: /{studentId} - профиль студента
* ✅ GET: / - все студенты
* ✅ GET: /{studentId}/details - детали профиля пользователя
* ✅ PATCH: /{studentId}/details - редактирования профиля
* ✅ GET: /{studentId}/cv - получить CV студента
* ❌ POST: /{studentId}/cv - загрузить CV студента
* ✅ DELETE: /{studentId} - удаление аккаунта студента
* ✅ GET: /{studentId}/respondedVacancies - список откликнувшихся вакансий

#### Company
* ✅ GET: /{companyId} - профиль компании
* ✅ GET: / - все компании
* ✅ GET: /{companyId}/detail - детали профиля компании
* ✅ PATCH: /{companyId}/edit - редактирования профиля
* ✅ DELETE: /{companyId} - удаление аккаунта компании
* ✅ GET: /{companyId}/vacancies - список вакансий компании

#### Vacancies
* GET: /vacancies/{vacancyId} - страница вакансии
* POST: /vacancies - создать вакансию
* PUT: /vacancies/{vacancyId} - редактировать вакансию
* DELETE: /vacancies/{vacancyId} - удаление вакансии
* GET: /vacancies/{vacancyId}/responders - список откликнувшихся студентов
* POST: /vacancies/{vacancyId}/respond - откликнуться на вакансию
* POST: /vacancies/{vacancyId}/cancelRespond - отменить отклик на вакансию


