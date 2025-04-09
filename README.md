# Progra3_Frutillas

Compilar clases
```
javac -d bin -sourcepath src src/com/frutilla/test/Principal.java
```

Ejecutar
```
java -cp bin Principal
```

Compilación .jar
```
javac -cp "lib/*" -d bin src\com\frutilla\test\Principal.java
```

Ejecutar .jar
```
java -cp "bin;lib/*" Principal
```