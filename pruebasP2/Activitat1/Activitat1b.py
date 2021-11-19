from functools import reduce
print("Introduce un numero: ")

def menor(n):
    if (n > 10):
        return n

def mutiliplicar(a,b):
    res = a * b
    return res

#pide un numero por pantalla al usuario y lo separa en una lista
listaog = input().split(" ")
#mapea la lista transformando a integer cada uno de los numeros que hay en esta
numeros = map(int,listaog)
"""utilizando filter envia cada número de la lista a la función menor 
para comprobar si son menores que 10 y así filtrarlos"""
filtrado = list(filter(menor, numeros))
#para poder pasar la lista filtrada al reduce hay que pasarla a list para que funcione
"""utilizando resume suma cada uno de los números de la lista para que
devuelva un único resultado"""
result = reduce(lambda x, y : x*y  , filtrado)


print(result)