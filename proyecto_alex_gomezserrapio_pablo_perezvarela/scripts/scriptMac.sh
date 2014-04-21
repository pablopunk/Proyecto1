#!/bin/bash

# Acceder a la carpeta
cd /Users/pablopunk/Workspace/Webs/DAWA/Proyecto1/proyecto_alex_gomezserrapio_pablo_perezvarela clear;
pwd

echo ">> Deteniendo el servidor..."
/Library/Tomcat/bin/shutdown.sh 1>/dev/null

echo ">> Compilando clases de java..."
find . -name "*.java" > scripts/sources.txt
javac -cp WEB-INF/lib/servlet-api.jar:WEB-INF/lib/mysql-connector-java-5.1.27-bin.jar:WEB-INF/lib/mail.jar @scripts/sources.txt 1>/dev/null
rm scripts/sources.txt

echo ">> Creando archivo .war..."
jar cvf proyecto_alex_gomezserrapio_pablo_perezvarela.war * 1>/dev/null

echo ">> Borrando servlets anteriores"
rm -rf /Library/Tomcat/webapps/proyecto_alex_gomezserrapio_pablo_perezvarela* 1>/dev/null

echo ">> Moviendo servlet a la carpeta de apache..."
mv proyecto_alex_gomezserrapio_pablo_perezvarela.war /Library/Tomcat/webapps/ 1>/dev/null

echo ">> Reiniciando el servidor..."
/Library/Tomcat/bin/startup.sh 1>/dev/null

echo ">> Hecho!"
