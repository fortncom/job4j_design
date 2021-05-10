# job4j_design
[![Build Status](https://travis-ci.com/fortncom/job4j_design.svg?branch=master)](https://travis-ci.com/fortncom/job4j_design)
[![codecov](https://codecov.io/gh/fortncom/job4j_design/branch/master/graph/badge.svg)](https://codecov.io/gh/fortncom/job4j_design)

Инструкция по сборке и запуску задачи mail.

1. Установить Maven по ссылке. https://maven.apache.org/download.cgi.
2. Создать новую переменную среды M2_HOME. Значение указать до папки apache-maven-.. .
3. Скачать zip архив проекта по ссылке https://github.com/fortncom/job4j_design.
4. Распаковать архив в папку c:\projects\ .
5. Через командную строку:
 - переставить курсор на папку с проектом командой  "cd C:\projects\job4j_design"
 - cкомпилировать проект "mvn compile"
 - запускаем командой "java -cp C:\projects\job4j_design\target\classes ru.job4j.mail.StartUI"

