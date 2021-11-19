import re
cad = "Hola Ho hola abc abcer 00 0000 000000 101 10112 10113"

coincide = 0

#comprobar cadena 00

#en primer lugar se compila el patrón para poder buscarlo más tarde
pat1 = re.compile(".*00.*")


def compr1(cad):
    #para comprobar si coincide se contrasta el patron establecido anteriormente con la cadena mediante match
    if (pat1.match(cad.lower())):
        return True

"""creacion de una cadena provisional, la cual irá variando en cada bucle para coger
el trozo de la string que sea adecuado"""
provisional = ""

"""Puesto que sabemos que queremos buscar una cadena que empieza por 0,
se busca el primer 0 que aparezca para asi ahorrarnos todos los caracteres 
anteriores y guarda esta posicion en char"""
char = cad.find("0")

"""bucle que se repite mietras que la posicion en la que estamos (char) sea menor que 
la longitud de la cadena"""
while (char < len(cad)):
    #se asigna una cadena con 3 caracteres para buscar en estos el patron
    provisional = cad[char:char+2]
    if (compr1(provisional)): #llama a compr1 para ver si coincide
        #si el patron coincide, se suma una coincidencia
        coincide += 1
    """coincida o no, se suma 1 a char para que avance una posicion el fragmento de cadena
    que se coge y se analice de nuevo""" 
    char+=1

#comprobar resto de cadenas
"""para comprobar estas cadenas, cad, es decir, la cadena a comprobar se pasa a lower
para que no haya diferencia entre mayúsculas y minúsculas y después aplico la
función count, que contara cuantas veces se repite la cadena que paso a esta 
como parámetro (con estos patrones se puede hacer de esta forma ya que no hay solapamiento)"""
coincide += cad.lower().count("101")
coincide += cad.lower().count("abc")
coincide += cad.lower().count("ho")

print("Hay "+str(coincide)+" coincidencias")