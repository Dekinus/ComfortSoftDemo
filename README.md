## ComfortSoft

### Используемые технологии

+ Java 17 
+ Maven 3.9.4
+ Spring Boot 3.4.5

#### Запуск

1. Собираем проект
2. Запускаем проект
4. * Вызываем POST метод по адресу localhost:8080/xlsx/demo через Postman (не забываем о body)\
     \
   или

   * Вызываем метод xlsx/demo метод в swagger

### Пример тела запроса

`{
  "filePath": "C:\\testFile.xlsx",
  "number": 3
}`

В поле filePath передаётся путь до файла, хранящегося на локальной машине
