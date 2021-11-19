import Professor, Escola, Alumne 

#Creamos personas de ejemplo, tanto profesores como alumnos
Professor1 = Professor.Professor("Ejemplo","mixto")
Alumno1 = Alumne.Alumne("Adrian", "1", Professor1)
Professor2 = Professor.Professor("Ejemplo2","letras")
Alumno2 = Alumne.Alumne("Adrian2", "2", Professor2)
Escuela1 = Escola.Escola("Escuela1", "Valencia", "Director")

#Se muestran las personas para ver sus carácteristicas
print(Professor1)
print(Alumno1)
print(Professor2)
print(Alumno2)

#Se añaden los alumnos a la lista de alumnos de la escuela
Escola.Escola.add_Alum(Escuela1,Alumno1)
Escola.Escola.add_Prof(Escuela1,Professor1)
Escola.Escola.add_Alum(Escuela1,Alumno2)
Escola.Escola.add_Prof(Escuela1,Professor2)

#Printeamos la lista de alumnos
print("\nLista de alumnos: ")
for x in range(len(Escola.Escola.alumnos)):
    print(Escola.Escola.alumnos[x])

#Printeamos la lista de profesores
print("\nLista de profesor: ")
for x in range(len(Escola.Escola.profesores)):
    print(Escola.Escola.alumnos[x])

#Se elimina un alumno
Escola.Escola.del_Alum(Escuela1,Alumno2)

#Se printea de nuevo la lista de alumnos para ver que este se ha eliminado
print("\nLista de alumnos: ")
for x in range(len(Escola.Escola.alumnos)):
    print(Escola.Escola.alumnos[x])